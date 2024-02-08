package microService.example.microService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateLastPulldto {
    @JsonProperty("images")
    private Image[] images;

    public Image[] getImages() {
        return images;
    }

    public void setImages(Image[] images) {
        this.images = images;
    }

    public static class Image {
        @JsonProperty("last_pulled")
        private String lastPulled;

        public String getLastPulled() {
            return lastPulled;
        }

        public void setLastPulled(String lastPulled) {
            this.lastPulled = lastPulled;
        }
    }
}
