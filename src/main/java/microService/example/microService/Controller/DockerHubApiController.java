package microService.example.microService.Controller;


import microService.example.microService.Interface.DockerHubApi;
import microService.example.microService.Interface.DockerRepositoryTransfer;
import microService.example.microService.Service.DockerRepositoryTransferImp;
import microService.example.microService.dto.getImagedto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/versionControl")
public class DockerHubApiController {
    @Autowired
    DockerHubApi dockerHubApi;
    @Autowired
    DockerRepositoryTransfer dockerRepositoryTransfer;

    @GetMapping("/checkForUpdate")
    public ResponseEntity<List<getImagedto>> saveAndFetchRepository(){
        List<getImagedto> images = dockerHubApi.getAllDockerRepository();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @GetMapping("/getProductVersion")
    public  ResponseEntity<List<getImagedto>> FetchRepository(){
        List<getImagedto> images= dockerRepositoryTransfer.getAllRepository();
        return new ResponseEntity<>(images,HttpStatus.OK);
    }


}
