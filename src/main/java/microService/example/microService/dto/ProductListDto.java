package microService.example.microService.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductListDto {
    private String version;
    private String changeLog;
    private String knownFix;
    private LocalDateTime lastPull;
}
