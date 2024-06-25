package microService.example.microService.Service;

import microService.example.microService.Entity.ProductList;
import microService.example.microService.Interface.CompatibleImp;
import microService.example.microService.Repository.ProductListRepository;
import microService.example.microService.dto.CompatibilityCheckResult;
import microService.example.microService.dto.VersionSetProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class Compatible implements CompatibleImp {

    @Autowired
    ProductListRepository productListRepository;

    public CompatibilityCheckResult checkBothCompatibilityProductVersions(VersionSetProductDto product1 , VersionSetProductDto product2){
        String productname1 = product1.getProductName();
        String productversion1 = product1.getProductSetVersion();
        String productname2 = product2.getProductName();
        String productversion2 = product2.getProductSetVersion();

        // Find the ProductList entity for productname1 and productversion1
        Optional<ProductList> productOpt1 = productListRepository.findByProductAndVersion(productname1, productversion1);
        if (productOpt1.isPresent()) {
            ProductList product1Entity = productOpt1.get();
            String compatibleVersions1 = product1Entity.getCompatible();
            if (compatibleVersions1 != null && !compatibleVersions1.isEmpty()) {
                // Assuming compatible contains comma-separated versions
                String[] compatibleVersions = product1Entity.getCompatible().split(",");
                for (String compatibleVersion : compatibleVersions) {
                    if (compatibleVersion.trim().equals(productversion2)) {
                        return new CompatibilityCheckResult(true, new ArrayList<>());  // productversion2 is compatible
                    }
                }
            }
        }

        if (productOpt1.isPresent()) {
            ProductList product1Entity = productOpt1.get();
            String compatibleVersions1 = product1Entity.getCompatible();
            if (compatibleVersions1 != null && !compatibleVersions1.isEmpty()) {
                // Check if product2 is present and if productversion1 exists with a greater id than productversion2
                Optional<ProductList> productOpt2 = productListRepository.findByProductAndVersion(productname2, product1Entity.getCompatible());
                Optional<ProductList> productOpt3 = productListRepository.findByProductAndVersion(productname2, productversion2);
                if (productOpt2.isPresent() && productOpt3.isPresent()) {
                    ProductList product2Entity = productOpt2.get();
                    ProductList product3Entity = productOpt3.get();
                    if (product2Entity.getId() <= product3Entity.getId()) {
                        return new CompatibilityCheckResult(true, new ArrayList<>());
                    }
                }
            }
        }

        // If compatibility check fails, check if productname2 and productversion2 exist
        if (productOpt1.isPresent()) {
            ProductList product1Entity = productOpt1.get();
            String compatibleVersions1 = product1Entity.getCompatible();
            Optional<ProductList> productOpt2 = productListRepository.findByProductAndVersion(productname2, product1Entity.getCompatible());
            if (productOpt2.isPresent()) {
                ProductList product2Entity = productOpt2.get();
                // Check if productversion1 exists with a greater id than productversion2
                List<ProductList> productsWithGreaterId = productListRepository.findAllByProductAndId(productname2, product2Entity.getId());
                if (!productsWithGreaterId.isEmpty()) {
                    return new CompatibilityCheckResult(false, productsWithGreaterId);
                }
            }
        }

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

        return new CompatibilityCheckResult(false, new ArrayList<>());
    }


}
