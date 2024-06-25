package microService.example.microService.Controller;

import microService.example.microService.Interface.DockerRepositoryTransfer;
import microService.example.microService.Service.DockerRepositoryTransferImp;
import microService.example.microService.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/v1/globalSDN/SiteManagement")
@CrossOrigin
@PreAuthorize("hasAnyRole('client_user', 'client_admin')")
public class SiteManagementController {

    @Autowired
    DockerRepositoryTransfer dockerRepositoryTransfer;
//    return the upgrade verrsion
    @PostMapping("/getUpgradeVersion")
    public ResponseEntity<List<ImageTransfer>> aboveVersion(@RequestBody List<ImageDto> requestDTOList){
        List<ImageTransfer> images = dockerRepositoryTransfer.getIterationVersions(requestDTOList,"above");
        return new ResponseEntity<>(images, HttpStatus.OK);
    }
//     return the Downgrade verrsion
    @PostMapping("/getDowngradeVersion")
    public  ResponseEntity<List<ImageTransfer>> belowVersion(@RequestBody List<ImageDto> requestDTOList){
        List<ImageTransfer> images = dockerRepositoryTransfer.getIterationVersions(requestDTOList,"below");
        return new ResponseEntity<>(images,HttpStatus.OK);

    }

    @PostMapping("/checkBatchCompatibility")
    public CompatibilityCheckResultdto checkBatchCompatibility(@RequestBody List<VersionSetProductDto> versionSetProductDtos) {
        CompatibilityCheckResultdto result = dockerRepositoryTransfer.processProductVersions(versionSetProductDtos);
        return result;
//        if (result.isCompatible()) {
//            return true;
//        } else {
//            return result.getProductsWithGreaterId();
//        }
    }
}
