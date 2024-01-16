package microService.example.microService.dto;

import java.util.List;

public class DockerApiResponse {
    private int count;
    private List<DockerRepositoryResponse> results;

    public DockerApiResponse(int count, List<DockerRepositoryResponse> results) {
        this.count = count;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DockerRepositoryResponse> getResults() {
        return results;
    }

    public void setResults(List<DockerRepositoryResponse> results) {
        this.results = results;
    }
}