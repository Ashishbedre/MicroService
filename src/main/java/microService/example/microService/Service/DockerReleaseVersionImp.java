package microService.example.microService.Service;

import microService.example.microService.Entity.ProductList;
import microService.example.microService.Interface.DockerReleaseVersion;
import microService.example.microService.Interface.DockerReleaseVersionHelper;
import microService.example.microService.Interface.ProductListDetail;
import microService.example.microService.Repository.ProductListRepository;
import microService.example.microService.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DockerReleaseVersionImp implements DockerReleaseVersion {

    @Autowired
    ProductListRepository productListRepository;

    @Autowired
    DockerReleaseVersionHelper dockerReleaseVersionHelper;


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
        // Fetch data from the Docker API
        DockerRepositoryImageResponse response = webClient.get()
                .uri("https://hub.docker.com/v2/repositories/niralnetworks/"+productName+"/tags/")
                .retrieve()
                .bodyToMono(DockerRepositoryImageResponse.class)
                .block();

        // Get the list of all existing product names from the database
        List<String> existingProductNames = productListRepository.findVersionsByProduct(productName);

        // Initialize a list to store new ProductList entities
        List<ProductListReleaseVersion> newProductList = new ArrayList<>();

        // Iterate over the results from the Docker API
        for (DockerRepositoryImageResponse.Result result : response.getResults()) {
            String name = result.getName();

            // If the product name is not already in the database, create a new ProductList entity
            if (!existingProductNames.contains(name)) {
                DockerRepositoryImageResponse.Image[] lastPushed = result.getImages();

                // Create a new ProductList entity with the extracted data
                ProductListReleaseVersion productListReleaseVersion = new ProductListReleaseVersion();
                productListReleaseVersion.setVersion(name);
                productListReleaseVersion.setPushedDate(lastPushed[0].getLastPushed());

                // Add the new entity to the list
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
