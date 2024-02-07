package microService.example.microService.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductListReleaseVersion {
    private Float version;
    private LocalDateTime pushedDate;
}
