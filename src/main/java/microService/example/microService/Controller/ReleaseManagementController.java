package microService.example.microService.Controller;


import microService.example.microService.Interface.ProductListDetail;
import microService.example.microService.dto.ImageDto;
import microService.example.microService.dto.ImageTransfer;
import microService.example.microService.dto.ProductListDto;
import microService.example.microService.dto.ProductListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/Release_management")
public class ReleaseManagementController {

    @Autowired
    private ProductListDetail productListDetail;
    @GetMapping("/productList")
    public ResponseEntity<List<ProductListResponse>> aboveVersion(){
        List<ProductListResponse> responce = productListDetail.getProductList();
        return new ResponseEntity<>(responce, HttpStatus.OK);
    }

    @GetMapping("/releaseVersionList/prodoctName={productName}")
    public ResponseEntity<List<ProductListDto>> releaseVersionList(@PathVariable String productName){
        List<ProductListDto> responce = productListDetail.getByProductNameAndVersion(productName);
        return new ResponseEntity<>(responce, HttpStatus.OK);
    }
    @DeleteMapping("/deleteReleaseVersion/{productName}/{version}")
    public ResponseEntity<String> deleteReleaseVersion(@PathVariable String productName,@PathVariable int version){
        productListDetail.deleteReleaseVersion(productName,version);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }


}
