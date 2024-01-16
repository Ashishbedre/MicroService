package microService.example.microService.Service;

import microService.example.microService.Interface.DockerHubApi;
import microService.example.microService.Interface.DockerRepositoryTransfer;
import microService.example.microService.config.AppConfig;
import microService.example.microService.config.Config;
import microService.example.microService.dto.DockerImageInfo;
import microService.example.microService.dto.DockerImageResult;
import microService.example.microService.dto.DockerRepository;
import microService.example.microService.dto.ResponceFormate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class DockerRepositoryImp  implements DockerRepositoryTransfer {
//
//
//    @Autowired
//    private AppConfig appConfig;
//    @Autowired
//    private Config config;
//    @Autowired
//    private DockerHubApi dockerHubApi;
//    @Value("${tag.api.url}")
//    private String tagUrl;
//
//    @Override
//    public List<ResponceFormate> getAllDockerRepository() {
//        List<DockerRepository> find_all =dockerHubApi.fetchAndSaveRepositories();
//        List<ResponceFormate> responceFormates = new ArrayList<>();
//        for (DockerRepository repositoryNameAndNamespace : find_all) {
//            String dockerHubApiUrl = buildDockerHubApiUrl(repositoryNameAndNamespace.getNamespace(), repositoryNameAndNamespace.getName());
//            List<DockerImageResult> apiResponse = fetchAndSaveTags(dockerHubApiUrl);
//            ResponceFormate repositoryEntity = new ResponceFormate();
//            List<String> tags = new ArrayList<>();
//            for (DockerImageResult repo : apiResponse) {
//                tags.add(repo.getName());
//            }
//            repositoryEntity.setRepository(repositoryNameAndNamespace.getName());
//            repositoryEntity.setTags(tags);
//            responceFormates.add(repositoryEntity);
//        }
//        return responceFormates;
//    }
//
//    @Override
//    public String buildDockerHubApiUrl(String namespace, String repository) {
//        return tagUrl + namespace + "/repositories/" + repository + "/tags";
//    }
//
//    @Override
//    public List<DockerImageResult> fetchAndSaveTags(String apiUrl) {
//        WebClient webClient = WebClient.create();
//        String authorizationHeader = "Bearer " + appConfig.getGlobalVariable();
//        DockerImageInfo response = webClient.get()
//                .uri(apiUrl)
//                .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
//                .retrieve()
//                .bodyToMono(DockerImageInfo.class)
//                .block();
//        List<DockerImageResult> repositories = response.getResults();
//        if(repositories==null){
//            return new ArrayList<>();
//        }
//        return repositories;
//
//
//    }
//
//    @Override
//    public List<String> filterTheRepository(String repository) {
//        List<String> repositoryDetail = new ArrayList<>();
//        List<ResponceFormate> getDetail = getAllDockerRepository();
//        try{
//            for(ResponceFormate detail : getDetail) {
//                if (detail.getRepository().equals(repository)) {
//                    for (String tag : detail.getTags()) {
//                        repositoryDetail.add(tag);
//                    }
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return  repositoryDetail;
//    }
//
//    @Override
//    public boolean findTheTag(String repository, String tag) {
//        List<String> tagList = filterTheRepository(repository);
//        for(String tags : tagList){
//            if(tags.equals(tag)){
//                return true;
//            }
//        }
//        return false;
//    }


}
