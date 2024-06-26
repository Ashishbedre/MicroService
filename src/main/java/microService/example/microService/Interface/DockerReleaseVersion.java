package microService.example.microService.Interface;

import microService.example.microService.dto.*;

import java.time.LocalDateTime;
import java.util.List;

public interface DockerReleaseVersion {

    List<ProductNameResponce> productList();
    List<ProductListReleaseVersion> getAvaliableVersion(String productName);
    List<ProductListReleaseVersion> getCompatibleVersion(String productName);

    boolean createRelease(ProductListReleaseVersionSave productListReleaseVersionSave);


}
