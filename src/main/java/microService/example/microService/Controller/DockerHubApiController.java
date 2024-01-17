package microService.example.microService.Controller;


import microService.example.microService.Entity.Image;
import microService.example.microService.Service.DockerHubApiImp;
import microService.example.microService.Service.DockerRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/versionControl")
public class DockerHubApiController {
    @Autowired
    DockerHubApiImp dockerHubApiImp;
    @Autowired
    DockerRepositoryImp dockerRepositoryImp;

    @GetMapping("/checkForUpdate")
    public ResponseEntity<List<Image>> saveAndFetchRepository(){
        List<Image> images = dockerHubApiImp.getAllDockerRepository();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @GetMapping("/getProductVersion")
    public  ResponseEntity<List<Image>> FetchRepository(){
        List<Image> images= dockerRepositoryImp.getAllRepository();
        return new ResponseEntity<>(images,HttpStatus.OK);
    }



}
