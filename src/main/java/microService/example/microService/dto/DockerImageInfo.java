package microService.example.microService.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DockerImageInfo {

    private int count;
    private String next;
    private String previous;
    private List<DockerImageResult> results;

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

    public List<DockerImageResult> getResults() {
        return results;
    }

    public void setResults(List<DockerImageResult> results) {
        this.results = results;
    }
}

