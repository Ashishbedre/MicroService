package microService.example.microService.Service;

import microService.example.microService.Entity.ProductList;
import microService.example.microService.Interface.ProductListDetail;
import microService.example.microService.Repository.ProductListRepository;
import microService.example.microService.dto.ProductListDto;
import microService.example.microService.dto.ProductListResponse;
import microService.example.microService.dto.pullCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductListDetailImp implements ProductListDetail {
    @Autowired
    ProductListRepository productListRepository;

    @Value("${pull.api.url}")
    private String pullApiUrl;

    @Override
    public  List<ProductListResponse> getProductList() {
        List<String> response = productListRepository.findDistinctProductNames();
        List<ProductListResponse> responseSend= new ArrayList<>();
        for(String iterate : response){
            ProductListResponse assign = new ProductListResponse();
            assign.setRepositoryName(iterate);
            assign.setDownloads(pullCount(iterate));
            responseSend.add(assign);
        }
        return responseSend;
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
//        dto.setVersion("V"+ String.valueOf(productList.getVersion()));
        dto.setVersion(productList.getVersion());
        dto.setChangeLog(productList.getChangeLog());
        dto.setKnownFix(productList.getKnowFix());
        dto.setLastPull(productList.getLastPull());
        return dto;
    }

    private int pullCount(String repositoriesName){
        WebClient webClient = WebClient.create();

        // Define the Docker API endpoint URL
        String apiUrl = pullApiUrl+repositoriesName;
        Integer pullCount=0;

        // Retrieve the pull_count from the Docker API endpoint
        try {
             pullCount = webClient.get()
                    .uri(apiUrl)
                    .retrieve()
                    .bodyToMono(pullCount.class)
                    .block()
                    .getPullCount();
        }catch (Exception e){
            e.printStackTrace();
        }
        return pullCount;
    }


}
