package microService.example.microService.dto;

import lombok.Data;

@Data
public class Version {
    String version;
    public Version(String version) {
        this.version = version;
    }
}
