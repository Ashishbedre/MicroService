package microService.example.microService.Interface;

import microService.example.microService.dto.ProductListDto;

import java.util.List;

public interface ProductListDetail {
    public List<String> getProductList();
    public  List<ProductListDto> getByProductNameAndVersion(String productName);

    public void deleteReleaseVersion(String productName,int version);

}
