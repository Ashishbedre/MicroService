package microService.example.microService.Service;

import microService.example.microService.Entity.Image;
import microService.example.microService.Entity.ProductList;
import microService.example.microService.Interface.CompatibleImp;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DockerRepositoryTransferImp implements DockerRepositoryTransfer {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ProductListRepository productListRepository;

    @Autowired
    CompatibleImp compatibleImp;

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
        if(tag==null || tag.equals("null")) {
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

    @Override
    public CompatibilityCheckResultdto processProductVersions(List<VersionSetProductDto> versionSetProductDtos) {
        if ((versionSetProductDtos.get(0).getProductSetVersion()==null && versionSetProductDtos.get(1).getProductSetVersion()==null) ||
                (versionSetProductDtos.get(0).getProductSetVersion().equals("null") && versionSetProductDtos.get(1).getProductSetVersion().equals("null"))) {
            ArrayList<CompatibilityCheckResult> CompatibilityCheckResults = new ArrayList<>();
            CompatibilityCheckResults.add(new CompatibilityCheckResult(true, new ArrayList<>()));
            CompatibilityCheckResultdto compatibilityCheckResultdto = new CompatibilityCheckResultdto(CompatibilityCheckResults);
            return  compatibilityCheckResultdto;
        }else{
            VersionSetProductDto product1 = versionSetProductDtos.get(0);
            VersionSetProductDto product2 = versionSetProductDtos.get(1);
            Optional<ProductList> getLatestVerion1  = productListRepository.findByProductAndVersion(product1.getProductName(),product1.getProductSetVersion());
            Optional<ProductList> getLatestVerion2  = productListRepository.findByProductAndVersion(product2.getProductName(),product2.getProductSetVersion());
            if(getLatestVerion1.isEmpty() && getLatestVerion2.isEmpty()){
                ArrayList<CompatibilityCheckResult> CompatibilityCheckResults = new ArrayList<>();
                CompatibilityCheckResults.add(new CompatibilityCheckResult(false, new ArrayList<>()));
                CompatibilityCheckResultdto compatibilityCheckResultdto = new CompatibilityCheckResultdto(CompatibilityCheckResults);
                return  compatibilityCheckResultdto;
            }else if(getLatestVerion2.isPresent() && getLatestVerion1.isEmpty()){
                ArrayList<CompatibilityCheckResult> CompatibilityCheckResults = new ArrayList<>();
                CompatibilityCheckResults.add(compatibleImp.checkBothCompatibilityProductVersions(product2,product1));
                CompatibilityCheckResultdto compatibilityCheckResultdto = new CompatibilityCheckResultdto(CompatibilityCheckResults);
                return  compatibilityCheckResultdto;

            } else if (getLatestVerion1.isPresent() && getLatestVerion2.isEmpty()) {
                {
                    ArrayList<CompatibilityCheckResult> CompatibilityCheckResults = new ArrayList<>();
                    CompatibilityCheckResults.add(compatibleImp.checkBothCompatibilityProductVersions(product1,product2));
                    CompatibilityCheckResultdto compatibilityCheckResultdto = new CompatibilityCheckResultdto(CompatibilityCheckResults);
                    return  compatibilityCheckResultdto;
                }
            }else{
                ArrayList<CompatibilityCheckResult> CompatibilityCheckResults = new ArrayList<>();
                if(getLatestVerion1.get().getId()>getLatestVerion2.get().getId()){
                    CompatibilityCheckResults.add(compatibleImp.checkBothCompatibilityProductVersions(product1,product2));
                }else{
                    CompatibilityCheckResults.add(compatibleImp.checkBothCompatibilityProductVersions(product2,product1));
                }
                CompatibilityCheckResultdto compatibilityCheckResultdto = new CompatibilityCheckResultdto(CompatibilityCheckResults);
                return  compatibilityCheckResultdto;
            }
        }
//        ArrayList<CompatibilityCheckResult> CompatibilityCheckResults = new ArrayList<>();
//        CompatibilityCheckResults.add(compatibleImp.checkBothCompatibilityProductVersions(product1,product2));
////        CompatibilityCheckResults.add(compatibleImp.checkBothCompatibilityProductVersions(product2,product1));
//        CompatibilityCheckResultdto compatibilityCheckResultdto = new CompatibilityCheckResultdto(CompatibilityCheckResults);
//        return  compatibilityCheckResultdto;


          // productversion2 is not compatible, or conditions are not met
    }

//
//    public  CompatibilityCheckResult checkBothCompatibilityProductVersions(VersionSetProductDto product1 ,VersionSetProductDto product2){
//        String productname1 = product1.getProductName();
//        String productversion1 = product1.getProductSetVersion();
//        String productname2 = product2.getProductName();
//        String productversion2 = product2.getProductSetVersion();
//
//        // Find the ProductList entity for productname1 and productversion1
//        Optional<ProductList> productOpt1 = productListRepository.findByProductAndVersion(productname1, productversion1);
//        if (productOpt1.isPresent()) {
//            ProductList product1Entity = productOpt1.get();
//            String compatibleVersions1 = product1Entity.getCompatible();
//            if (compatibleVersions1 != null && !compatibleVersions1.isEmpty()) {
//                // Assuming compatible contains comma-separated versions
//                String[] compatibleVersions = product1Entity.getCompatible().split(",");
//                for (String compatibleVersion : compatibleVersions) {
//                    if (compatibleVersion.trim().equals(productversion2)) {
//                        return new CompatibilityCheckResult(true, new ArrayList<>());  // productversion2 is compatible
//                    }
//                }
//            }
//        }
//
//        if (productOpt1.isPresent()) {
//            ProductList product1Entity = productOpt1.get();
//            String compatibleVersions1 = product1Entity.getCompatible();
//            if (compatibleVersions1 != null && !compatibleVersions1.isEmpty()) {
//                // Check if product2 is present and if productversion1 exists with a greater id than productversion2
//                Optional<ProductList> productOpt2 = productListRepository.findByProductAndVersion(productname2, product1Entity.getCompatible());
//                Optional<ProductList> productOpt3 = productListRepository.findByProductAndVersion(productname2, productversion2);
//                if (productOpt2.isPresent() && productOpt3.isPresent()) {
//                    ProductList product2Entity = productOpt2.get();
//                    ProductList product3Entity = productOpt3.get();
//                    if (product2Entity.getId() <= product3Entity.getId()) {
//                        return new CompatibilityCheckResult(true, new ArrayList<>());
//                    }
//                }
//            }
//        }
//
//        // If compatibility check fails, check if productname2 and productversion2 exist
//        if (productOpt1.isPresent()) {
//            ProductList product1Entity = productOpt1.get();
//            String compatibleVersions1 = product1Entity.getCompatible();
//            Optional<ProductList> productOpt2 = productListRepository.findByProductAndVersion(productname2, product1Entity.getCompatible());
//            if (productOpt2.isPresent()) {
//                ProductList product2Entity = productOpt2.get();
//                // Check if productversion1 exists with a greater id than productversion2
//                List<ProductList> productsWithGreaterId = productListRepository.findAllByProductAndIdGreaterThanEqual(productname2, product2Entity.getId());
//                if (!productsWithGreaterId.isEmpty()) {
//                    return new CompatibilityCheckResult(false, productsWithGreaterId);
//                }
//            }
//        }
//
//        return new CompatibilityCheckResult(false, new ArrayList<>());
//    }
//


//    @Override
//    public List<ImageTransfer> getIterationVersions(List<ImageDto> requestDTOList, String direction) {
//        return requestDTOList.stream()
//                .map(imageDto -> {
//                    List<Image> images;
//
//                    if ("above".equalsIgnoreCase(direction)) {
//                        images = getAboveVersion(imageDto.getRepo(), imageDto.getTag());
//                    } else if ("below".equalsIgnoreCase(direction)) {
//                        images = getBelowVersion(imageDto.getRepo(), imageDto.getTag());
//                    } else {
//                        throw new IllegalArgumentException("Invalid direction parameter");
//                    }
//
//                    ImageTransfer image = new ImageTransfer();
//                    image.setProduct(imageDto.getRepo());
//                    image.setVersions(images.stream().map(it -> new Version(it.getTag())).collect(Collectors.toList()));
//                    return image;
//                })
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public CompatibilityCheckResult processProductVersions(List<VersionSetProductDto> versionSetProductDtos) {
//        if (versionSetProductDtos.size() != 2) {
//            throw new IllegalArgumentException("List size must be 2");
//        }
//
//        VersionSetProductDto product1 = versionSetProductDtos.get(0);
//        VersionSetProductDto product2 = versionSetProductDtos.get(1);
//
//        String productname1 = product1.getProductName();
//        String productversion1 = product1.getProductSetVersion();
//        String productname2 = product2.getProductName();
//        String productversion2 = product2.getProductSetVersion();
//
//        // Find the ProductList entity for productname1 and productversion1
//        Optional<ProductList> productOpt1 = productListRepository.findByProductAndVersion(productname1, productversion1);
//        if (productOpt1.isPresent()) {
//            ProductList product1Entity = productOpt1.get();
//            String compatibleVersions1 = product1Entity.getCompatible();
//            if (compatibleVersions1 != null && !compatibleVersions1.isEmpty()) {
//                // Assuming compatible contains comma-separated versions
//                String[] compatibleVersions = product1Entity.getCompatible().split(",");
//                for (String compatibleVersion : compatibleVersions) {
//                    if (compatibleVersion.trim().equals(productversion2)) {
//                        return new CompatibilityCheckResult(true, new ArrayList<>());  // productversion2 is compatible
//                    }
//                }
//            }
//        }
//
//
//        ProductList product1Entity = productOpt1.get();
//        String compatibleVersions1 = product1Entity.getCompatible();
//        if (compatibleVersions1 != null && !compatibleVersions1.isEmpty()) {
//            // Check if product2 is present and if productversion1 exists with a greater id than productversion2
//            Optional<ProductList> productOpt2 = productListRepository.findByProductAndVersion(productname2, product1Entity.getCompatible());
//            Optional<ProductList> productOpt3 = productListRepository.findByProductAndVersion(productname2, productversion2);
//            if (productOpt2.isPresent() && productOpt3.isPresent()){
//                ProductList product2Entity = productOpt2.get();
//                ProductList product3Entity = productOpt3.get();
//                if(product2Entity.getId()<=product3Entity.getId()){
//                    return new CompatibilityCheckResult(true, new ArrayList<>());
//                }
//            }
//
//        }
//
////        // If compatibility check fails, check if productname2 and productversion2 exist
////        Optional<ProductList> productOpt2 = productListRepository.findByProductAndVersion(productname2, productversion2);
////        if (productOpt2.isPresent()) {
////            ProductList product2Entity = productOpt2.get();
////            // Check if productversion1 exists with a greater id than productversion2
////            List<ProductList> productsWithGreaterId = productListRepository.findAllByProductAndIdGreaterThan(productname2, product2Entity.getId());
////            if (!productsWithGreaterId.isEmpty()) {
////                return new CompatibilityCheckResult(false, productsWithGreaterId);
////            }
////        }
////        ProductList product1Entity = productOpt1.get();
////        String compatibleVersions1 = product1Entity.getCompatible();
////        if (compatibleVersions1 != null && !compatibleVersions1.isEmpty()) {
////            // Check if product2 is present and if productversion1 exists with a greater id than productversion2
////            Optional<ProductList> productOpt2 = productListRepository.findByProductAndVersion(productname2, product1Entity.getCompatible());
////            if (productOpt2.isPresent()) {
////                ProductList product2Entity = productOpt2.get();
////                List<ProductList> productsWithGreaterId = productListRepository.findAllByProductAndIdGreaterThanEqual(productname2, product2Entity.getId());
////                if (!productsWithGreaterId.isEmpty()) {
////                    return new CompatibilityCheckResult(true, new ArrayList<>());  // productversion1 exists with a greater id than productversion2
////                }
////            }
////        }
//
//        // If compatibility check fails, check if productname2 and productversion2 exist
//        Optional<ProductList> productOpt2 = productListRepository.findByProductAndVersion(productname2, product1Entity.getCompatible());
//        if (productOpt2.isPresent()) {
//            ProductList product2Entity = productOpt2.get();
//            // Check if productversion1 exists with a greater id than productversion2
//            List<ProductList> productsWithGreaterId = productListRepository.findAllByProductAndIdGreaterThanEqual(productname2, product2Entity.getId());
//            if (!productsWithGreaterId.isEmpty()) {
//                return new CompatibilityCheckResult(false, productsWithGreaterId);
//            }
//        }
////        // Check if product2 is present and if productversion1 exists with a greater id than productversion2
////        Optional<ProductList> productOpt2 = productListRepository.findByProductAndVersion(productname2, productversion2);
////        if (productOpt2.isPresent()) {
////            ProductList product2Entity = productOpt2.get();
////            List<ProductList> productsWithGreaterId = productListRepository.findAllByProductAndIdGreaterThan(productname1, product2Entity.getId());
////            if (!productsWithGreaterId.isEmpty()) {
////                return new CompatibilityCheckResult(true, new ArrayList<>());  // productversion1 exists with a greater id than productversion2
////            }
////        }
//
//        return new CompatibilityCheckResult(false, new ArrayList<>());  // productversion2 is not compatible, or conditions are not met
//    }


}
