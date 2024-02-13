package microService.example.microService.dto;


import microService.example.microService.Entity.ProductList;


public class ProductListReleaseVersionSave {
    private String productname;
    private String productVersion;
    private String changeLog;
    private String knownFix;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
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
}
