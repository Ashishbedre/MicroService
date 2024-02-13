package microService.example.microService.dto;


import java.time.LocalDateTime;


public class ProductListDto {
    private String version;
    private String changeLog;
    private String knownFix;
    private LocalDateTime lastPull;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }

    public String getKnownFix() {
        return knownFix;
    }

    public void setKnownFix(String knownFix) {
        this.knownFix = knownFix;
    }

    public LocalDateTime getLastPull() {
        return lastPull;
    }

    public void setLastPull(LocalDateTime lastPull) {
        this.lastPull = lastPull;
    }
}
