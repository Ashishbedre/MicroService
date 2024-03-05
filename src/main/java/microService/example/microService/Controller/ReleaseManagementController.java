package microService.example.microService.Controller;


import microService.example.microService.Entity.ProductList;
import microService.example.microService.Interface.DockerReleaseManagement;
import microService.example.microService.dto.PackageRealeseManagerdto;
import microService.example.microService.dto.ProductListDto;
import microService.example.microService.dto.ProductListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Release_management")
public class ReleaseManagementController {

    @Autowired
    private DockerReleaseManagement dockerReleaseManagement;

//    return the list of product list and Download that is already release
    @GetMapping("/productListAndDownload")
    public ResponseEntity<List<ProductListResponse>> productListAndDownload(){
        List<ProductListResponse> responce = dockerReleaseManagement.getProductListAndDownload();
        return new ResponseEntity<>(responce, HttpStatus.OK);
    }
//    return the list of release version
    @GetMapping("/releaseVersionList/prodoctName={productName}")
    public ResponseEntity<List<ProductListDto>> releaseVersionList(@PathVariable String productName){
        List<ProductListDto> responce = dockerReleaseManagement.getByProductNameAndVersion(productName);
        return new ResponseEntity<>(responce, HttpStatus.OK);
    }
//    delete the version
    @DeleteMapping("/deleteReleaseVersion/prodoctName={productName}/versionName={version}")
    public ResponseEntity<String> deleteReleaseVersion(@PathVariable String productName,@PathVariable String version){
        dockerReleaseManagement.deleteReleaseVersion(productName,version);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

    //get latest version in productList Dashboard
    @GetMapping("/getLatestVerion")
    public  ResponseEntity<List<PackageRealeseManagerdto>> getLatestVerion(){
        return  new ResponseEntity<>(dockerReleaseManagement.getLatestProductVerions(),HttpStatus.OK);
    }


}
