package microService.example.microService.Interface;

import microService.example.microService.dto.ProductListDto;
import microService.example.microService.dto.ProductListResponse;

import java.util.List;

public interface DockerReleaseManagement {
    public List<ProductListResponse> getProductListAndDownload();
    public  List<ProductListDto> getByProductNameAndVersion(String productName);

    public void deleteReleaseVersion(String productName,String version);
//    public  String updateLastPull(ProductList productList);

}
