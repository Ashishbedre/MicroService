package microService.example.microService.Service;

import microService.example.microService.Entity.Image;
import microService.example.microService.Interface.DockerHubApi;
import microService.example.microService.Interface.DockerRepositoryTransfer;
import microService.example.microService.Repository.ImageRepository;
import microService.example.microService.config.AppConfig;
import microService.example.microService.config.Config;
import microService.example.microService.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DockerRepositoryImp  implements DockerRepositoryTransfer {

    @Autowired
    ImageRepository imageRepository;
    @Override
    public List<getImagedto> getAllRepository() {
        List<Image> getImage = imageRepository.findAll();
        List<getImagedto> transfer = new ArrayList<>();
        if(getImage!=null && !getImage.isEmpty()){
            for(Image dto : getImage){
                getImagedto Image = new getImagedto();
                Image.setId(dto.getId());
                Image.setProduct(dto.getRepo());
                Image.setVersion(dto.getTag());
                transfer.add(Image);
            }
        }
        return transfer;

    }

    @Override
    public List<Image> getAboveVersion(String repo,String tag) {
        Image aboveTag = imageRepository.findImageByTagAndRepo(repo,tag);
        List<Image> allAbove = new ArrayList<>();
        try {
            allAbove = imageRepository.findImagesAboveIdByRepo(repo,aboveTag.getId());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return allAbove;
    }

    @Override
    public List<Image> getBelowVersion(String repo,String tag) {
        Image aboveTag = imageRepository.findImageByTagAndRepo(repo,tag);
        List<Image> allAbove = new ArrayList<>();
        try {
            allAbove = imageRepository.findImagesBelowIdByRepo(repo, aboveTag.getId());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return allAbove;
    }


    @Override
    public List<ImageTransfer> getIterationVersions(List<ImageDto> requestDTOList, String direction) {
        return requestDTOList.stream()
                .map(imageDto -> {
                    List<Image> images;

                    if ("above".equalsIgnoreCase(direction)) {
                        images = getAboveVersion(imageDto.getRepo(), imageDto.getTag());
                    } else if ("below".equalsIgnoreCase(direction)) {
                        images = getBelowVersion(imageDto.getRepo(), imageDto.getTag());
                    } else {
                        throw new IllegalArgumentException("Invalid direction parameter");
                    }

                    ImageTransfer image = new ImageTransfer();
                    image.setProduct(images.isEmpty() ? "" : images.get(0).getRepo());
                    image.setVersions(images.stream().map(it -> new Version(it.getTag())).collect(Collectors.toList()));
                    return image;
                })
                .collect(Collectors.toList());
    }

}
