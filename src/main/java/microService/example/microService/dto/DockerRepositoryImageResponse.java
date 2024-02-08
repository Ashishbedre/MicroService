package microService.example.microService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DockerRepositoryImageResponse {
    private int count;
    private String next;
    private String previous;
    private Result[] results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public Result[] getResults() {
        return results;
    }

    public void setResults(Result[] results) {
        this.results = results;
    }

    public static class Result {
        private long creator;
        private long id;
        private Image[] images;
        @JsonProperty("last_updated")
        private String lastUpdated;
        @JsonProperty("last_updater")
        private long lastUpdater;
        @JsonProperty("last_updater_username")
        private String lastUpdaterUsername;
        private String name;
        private long repository;
        @JsonProperty("full_size")
        private long fullSize;
        private boolean v2;
        @JsonProperty("tag_status")
        private String tagStatus;
        @JsonProperty("tag_last_pulled")
        private String tagLastPulled;
        @JsonProperty("tag_last_pushed")
        private String tagLastPushed;
        @JsonProperty("media_type")
        private String mediaType;
        @JsonProperty("content_type")
        private String contentType;
        private String digest;

        public long getCreator() {
            return creator;
        }

        public void setCreator(long creator) {
            this.creator = creator;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Image[] getImages() {
            return images;
        }

        public void setImages(Image[] images) {
            this.images = images;
        }

        public String getLastUpdated() {
            return lastUpdated;
        }

        public void setLastUpdated(String lastUpdated) {
            this.lastUpdated = lastUpdated;
        }

        public long getLastUpdater() {
            return lastUpdater;
        }

        public void setLastUpdater(long lastUpdater) {
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
// Getters and setters for all fields
    }


    //Image
    public static class Image {
        private String architecture;
        private String features;
        private String variant;
        private String digest;
        private String os;
        @JsonProperty("os_features")
        private String osFeatures;
        @JsonProperty("os_version")
        private String osVersion;
        private long size;
        private String status;
        @JsonProperty("last_pulled")
        private String lastPulled;
        @JsonProperty("last_pushed")
        private String lastPushed;

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
// Getters and setters for all fields
    }

}
