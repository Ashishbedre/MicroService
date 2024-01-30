package microService.example.microService.Service;

import microService.example.microService.Entity.ProductList;
import microService.example.microService.Interface.ProductListDetail;
import microService.example.microService.Repository.ProductListRepository;
import microService.example.microService.dto.ProductListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductListDetailImp implements ProductListDetail {
    @Autowired
    ProductListRepository productListRepository;

    @Override
    public List<String> getProductList() {
        return productListRepository.findDistinctProductNames();
    }

    @Override
    public List<ProductListDto> getByProductNameAndVersion(String productName) {
        List<ProductList> response =  productListRepository.findByProduct(productName);
        List<ProductListDto> dtoList = convertToDtoList(response);

        return dtoList;
    }

    @Override
    public void deleteReleaseVersion(String productName, int version) {
        productListRepository.deleteByProductAndVersion(productName,version);
    }

    private List<ProductListDto> convertToDtoList(List<ProductList> productList) {
        return productList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ProductListDto convertToDto(ProductList productList) {
        ProductListDto dto = new ProductListDto();
        dto.setVersion("V"+ String.valueOf(productList.getVersion()));
        dto.setChangeLog(productList.getChangeLog());
        dto.setKnownFix(productList.getKnowFix());
        dto.setDownload(productList.getDownload());
        return dto;
    }
}
