package microService.example.microService.dto;

import java.util.List;


public class ImageTransfer {

    private  String product ;

    private List<Version> versions;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public List<Version> getVersions() {
        return versions;
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }
}
