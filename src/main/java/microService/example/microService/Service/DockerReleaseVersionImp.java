package microService.example.microService.Service;

import microService.example.microService.Entity.ProductList;
import microService.example.microService.Interface.DockerReleaseVersion;
import microService.example.microService.Interface.DockerReleaseVersionHelper;
import microService.example.microService.Repository.ProductListRepository;
import microService.example.microService.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class DockerReleaseVersionImp implements DockerReleaseVersion {

    @Autowired
    ProductListRepository productListRepository;

    @Autowired
    DockerReleaseVersionHelper dockerReleaseVersionHelper;

    @Value("${pull.api.url}")
    private String pullApiUrl;


    @Override
    public List<ProductNameResponce> productList() {
        List<String> response = productListRepository.findDistinctProductNames();
        List<ProductNameResponce> responseSend= new ArrayList<>();
        for(String iterate : response){
            ProductNameResponce assign = new ProductNameResponce();
            assign.setRepositoryName(iterate);
            responseSend.add(assign);
        }
        return responseSend;
    }

    @Override
    public List<ProductListReleaseVersion> getAvaliableVersion(String productName) {
        WebClient webClient = WebClient.create();
        DockerRepositoryImageResponse response = webClient.get()
                .uri(pullApiUrl+productName+"/tags/")
                .retrieve()
                .bodyToMono(DockerRepositoryImageResponse.class)
                .block();

        List<String> existingProductNames = productListRepository.findVersionsByProduct(productName);
        List<ProductListReleaseVersion> newProductList = new ArrayList<>();
        for (DockerRepositoryImageResponse.Result result : response.getResults()) {
            String name = result.getName();
            if (!existingProductNames.contains(name)) {
                DockerRepositoryImageResponse.Image[] lastPushed = result.getImages();
                ProductListReleaseVersion productListReleaseVersion = new ProductListReleaseVersion();
                productListReleaseVersion.setVersion(name);
                productListReleaseVersion.setPushedDate(lastPushed[0].getLastPushed());
                newProductList.add(productListReleaseVersion);
            }
        }

        Collections.sort(newProductList, Comparator.comparing(ProductListReleaseVersion::getPushedDate, Comparator.reverseOrder()));
        return newProductList;
    }

    @Override
    public boolean createRelease(ProductListReleaseVersionSave productListReleaseVersionSave) {
        ProductList createRelease = new ProductList();
        createRelease.setVersion(productListReleaseVersionSave.getProductVersion());
        createRelease.setProduct(productListReleaseVersionSave.getProductname());
        createRelease.setChangeLog(productListReleaseVersionSave.getChangeLog());
        createRelease.setKnowFix(productListReleaseVersionSave.getKnownFix());
        createRelease.setLastPull(dockerReleaseVersionHelper.dateTimeConverter(dockerReleaseVersionHelper.updateLastPull(createRelease)));
        try{
            productListRepository.save(createRelease);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
