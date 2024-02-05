package microService.example.microService.dto;

import lombok.Data;

@Data
public class ProductListDto {
    private String version;
    private String changeLog;
    private String knownFix;
    private int downloads;
}
