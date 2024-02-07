package microService.example.microService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class pullCount {
    @JsonProperty("pull_count")
    private Integer pullCount;

    public Integer getPullCount() {
        return pullCount;
    }

    public void setPullCount(Integer pullCount) {
        this.pullCount = pullCount;
    }
}
