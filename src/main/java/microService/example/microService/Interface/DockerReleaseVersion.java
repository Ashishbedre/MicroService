package microService.example.microService.Interface;

import microService.example.microService.dto.*;

import java.util.List;

public interface DockerReleaseVersion {

    List<ProductNameResponce> ProductList();
    List<ProductListReleaseVersion> GetAvaliableVersion();

    boolean CreateRelease(ProductListReleaseVersionSave productListReleaseVersionSave);

}
