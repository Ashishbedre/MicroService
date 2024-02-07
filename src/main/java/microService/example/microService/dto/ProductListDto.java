package microService.example.microService.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductListDto {
    private float version;
    private String changeLog;
    private String knownFix;
    private LocalDateTime lastPull;
}
