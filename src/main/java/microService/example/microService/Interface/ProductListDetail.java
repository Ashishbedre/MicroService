package microService.example.microService.Interface;

import microService.example.microService.dto.ProductListDto;
import microService.example.microService.dto.ProductListResponse;

import java.util.List;
import java.util.Map;

public interface ProductListDetail {
    public List<ProductListResponse> getProductList();
    public  List<ProductListDto> getByProductNameAndVersion(String productName);

    public void deleteReleaseVersion(String productName,int version);

}
