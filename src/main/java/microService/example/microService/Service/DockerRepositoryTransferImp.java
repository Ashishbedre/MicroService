package microService.example.microService.Service;

import microService.example.microService.Entity.Image;
import microService.example.microService.Entity.ProductList;
import microService.example.microService.Interface.DockerRepositoryTransfer;
import microService.example.microService.Repository.ImageRepository;
import microService.example.microService.Repository.ProductListRepository;
import microService.example.microService.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DockerRepositoryTransferImp implements DockerRepositoryTransfer {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ProductListRepository productListRepository;
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
        Long aboveTag = productListRepository.findIdByProductAndVersion(repo,tag);

        List<Image> allAbove = new ArrayList<>();
        List<ProductList> productLists = new ArrayList<>();
        //Ashish change for tag is null
        if(tag==null) {
            aboveTag = productListRepository.findMaxIdByProduct(repo);
            Pageable pageable = PageRequest.of(0, 7);
            productLists = productListRepository.findAllDataByProductAndIdGreaterThanEqualOrderByidAsc(repo, aboveTag, pageable);
        }else {
            productLists = productListRepository.findAllDataByProductAndIdLessThanOrderByidAsc(repo, aboveTag);
        }
        try {
            allAbove = productLists.stream()
                    .map(productList -> {
                        Image image = new Image();
                        image.setId(productList.getId());
                        image.setRepo(productList.getProduct());
                        image.setTag(productList.getVersion());
                        return image;
                    })
                    .collect(Collectors.toList());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return allAbove;
    }

    @Override
    public List<Image> getBelowVersion(String repo,String tag) {
        Long aboveTag = productListRepository.findIdByProductAndVersion(repo,tag);
        List<Image> allAbove = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(0, 2);
            allAbove = productListRepository.findAllDataByProductAndIdMoreThanOrderByidAsc(repo, aboveTag,pageable).stream()
                    .map(productList -> {
                        Image image = new Image();
                        image.setId(productList.getId());
                        image.setRepo(productList.getProduct());
                        image.setTag(productList.getVersion());
                        return image;
                    })
                    .collect(Collectors.toList());
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
                    image.setProduct(imageDto.getRepo());
                    image.setVersions(images.stream().map(it -> new Version(it.getTag())).collect(Collectors.toList()));
                    return image;
                })
                .collect(Collectors.toList());
    }

}
