package microService.example.microService.Interface;

import microService.example.microService.Entity.Image;
import microService.example.microService.dto.*;

import java.util.ArrayList;
import java.util.List;

public interface DockerRepositoryTransfer {

    public List<getImagedto> getAllRepository();

    public List<Image> getAboveVersion(String repo,String tag);
    public  List<Image> getBelowVersion(String repo,String tag);
    public List<ImageTransfer> getIterationVersions(List<ImageDto> requestDTOList, String direction);

    public CompatibilityCheckResultdto processProductVersions(List<VersionSetProductDto> versionSetProductDtos);

//    public  List<ImageTransfer> getIterationAbove(List<ImageDto> requestDTOList);
//
//    public  List<ImageTransfer> getIterationBelow(List<ImageDto> requestDTOList);
//
//    public List<ResponceFormate> getAllDockerRepository();
//    //    public List<ResponceFormate> formatTheEntity(List<RepositoryEntity> repositoryEntityReturn);
//    public  String buildDockerHubApiUrl(String namespace, String repository);
//    public List<DockerImageResult> fetchAndSaveTags(String apiUrl);
//    public  List<String> filterTheRepository(String repository);
//
//    public boolean findTheTag(String repository, String tag);
}
