package microService.example.microService.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductListReleaseVersion {
    private String version;
    private String pushedDate;
}
