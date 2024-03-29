package microService.example.microService.Service;

import microService.example.microService.Entity.ProductList;
import microService.example.microService.Interface.DockerReleaseVersion;
import microService.example.microService.Interface.DockerReleaseVersionHelper;
import microService.example.microService.Interface.DockerReleaseManagement;
import microService.example.microService.Repository.ProductListRepository;
import microService.example.microService.dto.PackageRealeseManagerdto;
import microService.example.microService.dto.ProductListDto;
import microService.example.microService.dto.ProductListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class DockerReleaseManagementImp implements DockerReleaseManagement {
    @Autowired
    ProductListRepository productListRepository;

    @Autowired
    DockerReleaseVersionHelper dockerReleaseVersionHelper;

    @Autowired
    DockerReleaseVersion dockerReleaseVersion;
    @Value("${pull.api.url}")
    private String pullApiUrl;

    @Override
    public  List<ProductListResponse> getProductListAndDownload() {
        List<String> response = productListRepository.findDistinctProductNames();
        List<ProductListResponse> responseSend= new ArrayList<>();
        for(String iterate : response){
            ProductListResponse assign = new ProductListResponse();
            assign.setRepositoryName(iterate);
            assign.setDownloads(dockerReleaseVersionHelper.pullCount(iterate));
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
    public void deleteReleaseVersion(String productName, String version) {
        productListRepository.deleteByProductAndVersion(productName,version);
    }

    @Override
    public List<PackageRealeseManagerdto> getLatestProductVerions() {
        List<ProductList> findLatestVerionOfProductsConvert = productListRepository.findLatestVersionsOfProducts();
        List<PackageRealeseManagerdto> packageRealeseManagerdtos = new ArrayList<>();
        for(ProductList convertdto : findLatestVerionOfProductsConvert){
            PackageRealeseManagerdto packageRealeseManagerdto = new PackageRealeseManagerdto();
            packageRealeseManagerdto.setId(convertdto.getId());
            packageRealeseManagerdto.setProduct(convertdto.getProduct());
            packageRealeseManagerdto.setVersion(convertdto.getVersion());
            packageRealeseManagerdtos.add(packageRealeseManagerdto);
        }
        return packageRealeseManagerdtos;
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


}
