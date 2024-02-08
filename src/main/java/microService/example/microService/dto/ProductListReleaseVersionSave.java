package microService.example.microService.dto;

import lombok.Data;
import microService.example.microService.Entity.ProductList;

@Data
public class ProductListReleaseVersionSave {
    private String productname;
    private String productVersion;
    private String changeLog;
    private String knownFix;

}
