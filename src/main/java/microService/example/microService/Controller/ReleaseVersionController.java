package microService.example.microService.Controller;

import microService.example.microService.Interface.DockerReleaseVersion;
import microService.example.microService.dto.ProductListReleaseVersion;
import microService.example.microService.dto.ProductListReleaseVersionSave;
import microService.example.microService.dto.ProductListResponse;
import microService.example.microService.dto.ProductNameResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Release_version")
public class ReleaseVersionController {

    @Autowired
    DockerReleaseVersion dockerReleaseVersion;
    @GetMapping("/productList")
    public ResponseEntity<List<ProductNameResponce>> productName(){
        List<ProductNameResponce> responce = dockerReleaseVersion.productList();
        return new ResponseEntity<>(responce, HttpStatus.OK);
    }
    @GetMapping("/availableVersion/prodoctName={productName}")
    public ResponseEntity<List<ProductListReleaseVersion>> availableVersion(@PathVariable String productName){
        List<ProductListReleaseVersion> responce = dockerReleaseVersion.getAvaliableVersion(productName);
        return new ResponseEntity<>(responce, HttpStatus.OK);
    }

    @PostMapping("/saveReleaseVersion")
    public ResponseEntity<Boolean> saveReleaseVersion(@RequestBody ProductListReleaseVersionSave productListReleaseVersionSave){
        Boolean responce = dockerReleaseVersion.createRelease(productListReleaseVersionSave);
        return new ResponseEntity<>(responce, HttpStatus.OK);
    }

}
