package microService.example.microService.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import microService.example.microService.Entity.Image;
import microService.example.microService.Interface.DockerHubApi;
import microService.example.microService.Repository.ImageRepository;
import microService.example.microService.config.AppConfig;
import microService.example.microService.config.Config;
import microService.example.microService.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EnableScheduling
@Service
public class DockerHubApiImp implements DockerHubApi {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private  DockerRepositoryImp dockerRepositoryImp;
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private Config config;
    @Value("${login.api.url}")
    private String apiUrllogin;
    @Value("${repo.api.url}")
    private String repoUrl;

    @Value("${tag.api.url}")
    private String tagUrl;



    public String login(){
        WebClient webClient = WebClient.create();
        String jsonBody = "{\"username\": \"" +config.getUsername()+ "\", \"password\": \"" +config.getPassword()+ "\"}";
        String responseBody = webClient.post()
                .uri(apiUrllogin)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(jsonBody))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println(responseBody);
        ObjectMapper objectMapper = new ObjectMapper();
        String bearerToken="";
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            bearerToken = rootNode.get("token").asText();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bearerToken;
    }

    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
    public String gettoken(){
        String token = login();
        appConfig.setGlobalVariable(token);
        return token;
    }

    public List<DockerRepository> fetchAndSaveRepositories() {
        WebClient webClient = WebClient.create();
        String authorizationHeader = "Bearer " + appConfig.getGlobalVariable();
        DockerApiResponse response = webClient.get()
                .uri(buildDockerHubApiUrl())
                .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                .retrieve()
                .bodyToMono(DockerApiResponse.class)
                .block();


        List<DockerRepositoryResponse> repositories =response.getResults();
        List<DockerRepository> existingRepository = new ArrayList<>();
        if (repositories == null || repositories.isEmpty()) {
            System.out.println("No Docker repositories found or the list is empty.");
        } else {
            for (DockerRepositoryResponse repository : repositories) {
                String name = repository.getName();
                String namespace = repository.getNamespace();
                DockerRepository dockerRepository = new DockerRepository();
                dockerRepository.setName(name);
                dockerRepository.setNamespace(namespace);
                existingRepository.add(dockerRepository);
            }
        }
        return  existingRepository;
    }


    @Override
    public List<Image> getAllDockerRepository() {
        List<DockerRepository> find_all =fetchAndSaveRepositories();
        List<Image> responceFormates = new ArrayList<>();
        for (DockerRepository repositoryNameAndNamespace : find_all) {
            String dockerHubApiUrl = buildDockerHubApiUrl(repositoryNameAndNamespace.getNamespace(), repositoryNameAndNamespace.getName());
            List<DockerImageResult> apiResponse = fetchAndSaveTags(dockerHubApiUrl);
            update(apiResponse,repositoryNameAndNamespace.getName());
        }
        return dockerRepositoryImp.getAllRepository();
    }

    @Override
    public void update(List<DockerImageResult> apiResponse,String repository){
        for (DockerImageResult repo : apiResponse) {
            Optional<Image> existingEntity = imageRepository.findByRepoAndTag(repository, repo.getName());
            if (existingEntity.equals(null) || existingEntity.isEmpty()) {
                Image newEntity = new Image();
                newEntity.setRepo(repository);
                newEntity.setTag(repo.getName());
                imageRepository.save(newEntity);
            }
        }
    }

    @Override
    public List<DockerImageResult> fetchAndSaveTags(String apiUrl) {
        WebClient webClient = WebClient.create();
        String authorizationHeader = "Bearer " + appConfig.getGlobalVariable();
        DockerImageInfo response = webClient.get()
                .uri(apiUrl)
                .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                .retrieve()
                .bodyToMono(DockerImageInfo.class)
                .block();
        List<DockerImageResult> repositories = response.getResults();
        if(repositories==null){
            return new ArrayList<>();
        }
        return repositories;


    }
    @Override
    public String buildDockerHubApiUrl() {
        return repoUrl+config.getUsername()+"/repositories";
    }

    @Override
    public String buildDockerHubApiUrl(String namespace, String repository) {
        return tagUrl + namespace + "/repositories/" + repository + "/tags";
    }

}
