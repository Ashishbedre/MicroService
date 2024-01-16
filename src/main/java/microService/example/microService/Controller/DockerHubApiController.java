package microService.example.microService.Controller;


import microService.example.microService.Entity.Image;
import microService.example.microService.Service.DockerHubApiImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class DockerHubApiController {
    @Autowired
    DockerHubApiImp dockerHubApiImp;

    @GetMapping("/saveAndFetchRepository")
    public ResponseEntity<List<Image>> saveAndFetchRepository(){
        List<Image> test = dockerHubApiImp.getAllDockerRepository();
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @GetMapping("/FetchRepository")
    public  ResponseEntity<String> FetchRepository(){
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

}
