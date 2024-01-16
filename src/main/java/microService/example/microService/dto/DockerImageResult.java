package microService.example.microService.dto;


import java.util.List;

public class DockerImageResult {

    private int creator;
    private long id;
    private List<DockerImage> images;
    private String lastUpdated;
    private int lastUpdater;
    private String lastUpdaterUsername;
    private String name;
    private long repository;
    private long fullSize;
    private boolean v2;
    private String tagStatus;
    private String tagLastPulled;
    private String tagLastPushed;
    private String mediaType;
    private String contentType;
    private String digest;

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<DockerImage> getImages() {
        return images;
    }

    public void setImages(List<DockerImage> images) {
        this.images = images;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getLastUpdater() {
        return lastUpdater;
    }

    public void setLastUpdater(int lastUpdater) {
        this.lastUpdater = lastUpdater;
    }

    public String getLastUpdaterUsername() {
        return lastUpdaterUsername;
    }

    public void setLastUpdaterUsername(String lastUpdaterUsername) {
        this.lastUpdaterUsername = lastUpdaterUsername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRepository() {
        return repository;
    }

    public void setRepository(long repository) {
        this.repository = repository;
    }

    public long getFullSize() {
        return fullSize;
    }

    public void setFullSize(long fullSize) {
        this.fullSize = fullSize;
    }

    public boolean isV2() {
        return v2;
    }

    public void setV2(boolean v2) {
        this.v2 = v2;
    }

    public String getTagStatus() {
        return tagStatus;
    }

    public void setTagStatus(String tagStatus) {
        this.tagStatus = tagStatus;
    }

    public String getTagLastPulled() {
        return tagLastPulled;
    }

    public void setTagLastPulled(String tagLastPulled) {
        this.tagLastPulled = tagLastPulled;
    }

    public String getTagLastPushed() {
        return tagLastPushed;
    }

    public void setTagLastPushed(String tagLastPushed) {
        this.tagLastPushed = tagLastPushed;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

}


