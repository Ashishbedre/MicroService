package microService.example.microService.Service;

import microService.example.microService.Entity.ProductList;
import microService.example.microService.Interface.DockerReleaseVersionHelper;
import microService.example.microService.Repository.ProductListRepository;
import microService.example.microService.config.AppConfig;
import microService.example.microService.dto.UpdateLastPulldto;
import microService.example.microService.dto.pullCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
@EnableScheduling
public class DockerReleaseVersionHelperImp implements DockerReleaseVersionHelper {

    @Autowired
    ProductListRepository productListRepository;
    @Value("${pull.api.url}")
    private String pullApiUrl;
    @Autowired
    private AppConfig appConfig;


    @Override
    public LocalDateTime dateTimeConverter(String inputDateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        return LocalDateTime.parse(inputDateTimeString, formatter);
    }

    @Override
    public int pullCount(String repositoriesName){
        WebClient webClient = WebClient.create();

        // Define the Docker API endpoint URL
        String apiUrl = pullApiUrl+repositoriesName;
        Integer pullCount=0;

        // Retrieve the pull_count from the Docker API endpoint
        try {
            pullCount = webClient.get()
                    .uri(apiUrl)
                    .retrieve()
                    .bodyToMono(microService.example.microService.dto.pullCount.class)
                    .block()
                    .getPullCount();
        }catch (Exception e){
            e.printStackTrace();
        }
        return pullCount;
    }

    //    @Scheduled(fixedDelay = 60000)
    public void saveLastPull() {
        List<ProductList> iterate = productListRepository.findAll();
        for(ProductList productList : iterate){
            productList.setLastPull(dateTimeConverter(updateLastPull(productList)));
            productListRepository.save(productList);
        }
    }

    @Override
    public  String updateLastPull(ProductList productList){
        String apiUrl = "https://hub.docker.com/v2/repositories/niralnetworks/"+productList.getProduct()+
                "/tags/"+productList.getVersion();
        UpdateLastPulldto response = new UpdateLastPulldto();
        try{
            WebClient webClient = WebClient.create();
            //Ashish change for token
            String authorizationHeader = "Bearer " + appConfig.getGlobalVariable();
            response = webClient.get()
                    .uri(apiUrl)
                    .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                    .retrieve()
                    .bodyToMono(UpdateLastPulldto.class)
                    .block();
        }catch (Exception e){
            e.printStackTrace();
        }
        // Extract the last pulled date from the response
        return response.getImages()[0].getLastPulled();
    }
}
