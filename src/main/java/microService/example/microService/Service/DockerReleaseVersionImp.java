package microService.example.microService.Service;

import microService.example.microService.Entity.ProductList;
import microService.example.microService.Interface.DockerReleaseVersion;
import microService.example.microService.Repository.ProductListRepository;
import microService.example.microService.dto.ProductListReleaseVersion;
import microService.example.microService.dto.ProductListReleaseVersionSave;
import microService.example.microService.dto.ProductListResponse;
import microService.example.microService.dto.ProductNameResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
@Service
public class DockerReleaseVersionImp implements DockerReleaseVersion {

    @Autowired
    ProductListRepository productListRepository;

    @Override
    public List<ProductNameResponce> ProductList() {
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
    public List<ProductListReleaseVersion> GetAvaliableVersion() {
        return null;
    }

    @Override
    public boolean CreateRelease(ProductListReleaseVersionSave productListReleaseVersionSave) {
        ProductList createRelease = new ProductList();
        createRelease.setVersion(productListReleaseVersionSave.getProductVersion());
        createRelease.setProduct(productListReleaseVersionSave.getProductname());
        createRelease.setChangeLog(productListReleaseVersionSave.getChangeLog());
        createRelease.setKnowFix(productListReleaseVersionSave.getKnownFix());
        createRelease.setLastPull(DateTimeConverter("2024-01-15T21:20:41.737293Z"));
        try{
            productListRepository.save(createRelease);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public LocalDateTime DateTimeConverter(String inputDateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        return LocalDateTime.parse(inputDateTimeString, formatter);

    }
}
