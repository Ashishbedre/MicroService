package microService.example.microService.dto;



import java.time.LocalDateTime;


public class ProductListReleaseVersion {
    private String version;
    private String pushedDate;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPushedDate() {
        return pushedDate;
    }

    public void setPushedDate(String pushedDate) {
        this.pushedDate = pushedDate;
    }
}
