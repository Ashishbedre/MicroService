package microService.example.microService.Controller;

import microService.example.microService.Interface.DockerReleaseVersion;
import microService.example.microService.dto.ProductListReleaseVersion;
import microService.example.microService.dto.ProductListReleaseVersionSave;
import microService.example.microService.dto.ProductListResponse;
import microService.example.microService.dto.ProductNameResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Release_version")
@CrossOrigin
@PreAuthorize("hasAnyRole('client_user', 'client_admin')")
public class ReleaseVersionController {

    @Autowired
    DockerReleaseVersion dockerReleaseVersion;

//    return the productList
    @GetMapping("/productList")
    public ResponseEntity<List<ProductNameResponce>> productName(){
        List<ProductNameResponce> responce = dockerReleaseVersion.productList();
        return new ResponseEntity<>(responce, HttpStatus.OK);
    }
//    return the available version
    @GetMapping("/availableVersion/prodoctName={productName}")
    public ResponseEntity<List<ProductListReleaseVersion>> availableVersion(@PathVariable String productName){
        List<ProductListReleaseVersion> responce = dockerReleaseVersion.getAvaliableVersion(productName);
        return new ResponseEntity<>(responce, HttpStatus.OK);
    }

    //    return the version for compartible with available version
    @GetMapping("/compatibleVersion/prodoctName={productName}")
    public ResponseEntity<List<ProductListReleaseVersion>> compatibleVersion(@PathVariable String productName){
        List<ProductListReleaseVersion> responce = dockerReleaseVersion.getCompatibleVersion(productName);
        return new ResponseEntity<>(responce, HttpStatus.OK);
    }
//    save the data
    @PostMapping("/saveReleaseVersion")
    public ResponseEntity<Boolean> saveReleaseVersion(@RequestBody ProductListReleaseVersionSave productListReleaseVersionSave){
        Boolean responce = dockerReleaseVersion.createRelease(productListReleaseVersionSave);
        return new ResponseEntity<>(responce, HttpStatus.OK);
    }

}
