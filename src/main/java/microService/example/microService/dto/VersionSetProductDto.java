package microService.example.microService.dto;

public class VersionSetProductDto {

    private String productName;
    private String productSetVersion;

    public VersionSetProductDto() {
    }

    public VersionSetProductDto(String productName, String productSetVersion) {
        this.productName = productName;
        this.productSetVersion = productSetVersion;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSetVersion() {
        return productSetVersion;
    }

    public void setProductSetVersion(String productSetVersion) {
        this.productSetVersion = productSetVersion;
    }

}
