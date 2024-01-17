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
    public List<Image> getAllRepository() {
        return imageRepository.findAll();
    }

    @Override
    public List<Image> getAboveVersion(String repo,String tag) {
        Image aboveTag = imageRepository.findImageByTagAndRepo(repo,tag);
        List<Image> allAbove = imageRepository.findImagesAboveIdByRepo(repo,aboveTag.getId());
        return allAbove;
    }

    @Override
    public List<Image> getBelowVersion(String repo,String tag) {
        Image aboveTag = imageRepository.findImageByTagAndRepo(repo,tag);
        List<Image> allAbove = imageRepository.findImagesBelowIdByRepo(repo,aboveTag.getId());
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
                    image.setRepo(images.isEmpty() ? "" : images.get(0).getRepo());
                    image.setTag(images.stream().map(Image::getTag).collect(Collectors.toList()));
                    return image;
                })
                .collect(Collectors.toList());
    }

//    @Override
//    public List<ImageTransfer> getIterationAbove(List<ImageDto> requestDTOList) {
//        List<ImageTransfer> iterationAbove = new ArrayList<>();
//        for(ImageDto ImageFetch:requestDTOList){
//        List<Image> images= getAboveVersion(ImageFetch.getRepo(),ImageFetch.getTag());
//        ImageTransfer image = new ImageTransfer();
//        List<String> get = new ArrayList<>();
//        String var = "";
//        for(Image it : images){
//            var=it.getRepo();
//            get.add(it.getTag());
//        }
//        image.setRepo(var);
//        image.setTag(get);
//        iterationAbove.add(image);
//        }
//        return iterationAbove;
//    }
//
//    @Override
//    public List<ImageTransfer> getIterationBelow(List<ImageDto> requestDTOList) {
//        List<ImageTransfer> iterationAbove = new ArrayList<>();
//        for(ImageDto ImageFetch:requestDTOList){
//            List<Image> images= getBelowVersion(ImageFetch.getRepo(),ImageFetch.getTag());;
//            ImageTransfer image = new ImageTransfer();
//            List<String> get = new ArrayList<>();
//            String var = "";
//            for(Image it : images){
//                var=it.getRepo();
//                get.add(it.getTag());
//            }
//            image.setRepo(var);
//            image.setTag(get);
//            iterationAbove.add(image);
//        }
//        return iterationAbove;
//    }



}
