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
        List<ProductNameResponce> responce = dockerReleaseVersion.ProductList();
        return new ResponseEntity<>(responce, HttpStatus.OK);
    }
    @GetMapping("/availableVersion")
    public ResponseEntity<List<ProductListReleaseVersion>> availableVersion(){
        List<ProductListReleaseVersion> responce = dockerReleaseVersion.GetAvaliableVersion();
        return new ResponseEntity<>(responce, HttpStatus.OK);
    }

    @PostMapping("/saveReleaseVersion")
    public ResponseEntity<Boolean> saveReleaseVersion(@RequestBody ProductListReleaseVersionSave productListReleaseVersionSave){
        Boolean responce = dockerReleaseVersion.CreateRelease(productListReleaseVersionSave);
        return new ResponseEntity<>(responce, HttpStatus.OK);
    }

}
