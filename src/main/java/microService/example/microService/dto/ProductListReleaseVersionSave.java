package microService.example.microService.dto;

import lombok.Data;
import microService.example.microService.Entity.ProductList;

@Data
public class ProductListReleaseVersionSave {
    private String productname;
    private Float productVersion;
    private String changeLog;
    private String knownFix;

}
