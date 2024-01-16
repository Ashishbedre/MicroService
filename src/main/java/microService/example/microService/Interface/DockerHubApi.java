package microService.example.microService.Interface;

import microService.example.microService.Entity.Image;
import microService.example.microService.dto.DockerImageResult;
import microService.example.microService.dto.DockerRepository;
import microService.example.microService.dto.ResponceFormate;

import java.util.List;

public interface DockerHubApi {
    public String login();
    public String gettoken();
    public List<DockerRepository> fetchAndSaveRepositories();

    public List<Image> getAllDockerRepository();
    public  String buildDockerHubApiUrl(String namespace, String repository);
    public List<DockerImageResult> fetchAndSaveTags(String apiUrl);
    public String buildDockerHubApiUrl();


}
