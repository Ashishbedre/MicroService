package microService.example.microService.dto;

public  class DockerImage {

    private String architecture;
    private String features;
    private String variant;
    private String digest;
    private String os;
    private String osFeatures;
    private String osVersion;
    private long size;
    private String status;
    private String lastPulled;
    private String lastPushed;

    // Constructors, getters, and setters


    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsFeatures() {
        return osFeatures;
    }

    public void setOsFeatures(String osFeatures) {
        this.osFeatures = osFeatures;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastPulled() {
        return lastPulled;
    }

    public void setLastPulled(String lastPulled) {
        this.lastPulled = lastPulled;
    }

    public String getLastPushed() {
        return lastPushed;
    }

    public void setLastPushed(String lastPushed) {
        this.lastPushed = lastPushed;
    }
}
