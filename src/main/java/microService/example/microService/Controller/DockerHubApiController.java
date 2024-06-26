package microService.example.microService.Controller;


import microService.example.microService.Interface.DockerHubApi;
import microService.example.microService.Interface.DockerRepositoryTransfer;
import microService.example.microService.Service.DockerRepositoryTransferImp;
import microService.example.microService.dto.getImagedto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/versionControl")
@CrossOrigin
@PreAuthorize("hasAnyRole('client_user', 'client_admin')")
public class DockerHubApiController {
    @Autowired
    DockerHubApi dockerHubApi;
    @Autowired
    DockerRepositoryTransfer dockerRepositoryTransfer;

//    Save the image data in DB and return the data
    @GetMapping("/checkForUpdate")
    public ResponseEntity<List<getImagedto>> saveAndFetchRepository(){
        List<getImagedto> images = dockerHubApi.getAllDockerRepository();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

//    return the image data
    @GetMapping("/getProductVersion")
    public  ResponseEntity<List<getImagedto>> FetchRepository(){
        List<getImagedto> images= dockerRepositoryTransfer.getAllRepository();
        return new ResponseEntity<>(images,HttpStatus.OK);
    }


}
