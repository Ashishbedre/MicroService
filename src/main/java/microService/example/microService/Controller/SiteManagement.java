package microService.example.microService.Controller;

import microService.example.microService.Entity.Image;
import microService.example.microService.Service.DockerRepositoryImp;
import microService.example.microService.dto.ImageDto;
import microService.example.microService.dto.ImageTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/v1/globalSDN/SiteManagement")
public class SiteManagement {

    @Autowired
    DockerRepositoryImp dockerRepositoryImp;

    @PostMapping("/getUpgradeVersion")
    public ResponseEntity<List<ImageTransfer>> aboveVersion(@RequestBody List<ImageDto> requestDTOList){
        List<ImageTransfer> images = dockerRepositoryImp.getIterationAbove(requestDTOList);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @PostMapping("/getDowngradeVersion/{repo}/{tag}")
    public  ResponseEntity<List<ImageTransfer>> belowVersion(@RequestBody List<ImageDto> requestDTOList){
        List<ImageTransfer> images = dockerRepositoryImp.getIterationBelow(requestDTOList);
        return new ResponseEntity<>(images,HttpStatus.OK);

    }
}