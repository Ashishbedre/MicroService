package microService.example.microService.dto;


import java.util.List;

public class DockerRepositoryResponse {
    private String name;
    private String namespace;
    private String repository_type;
    private int status;
    private String status_description;
    private String description;
    private boolean is_private;
    private int star_count;
    private int pull_count;
    private String last_updated;
    private String date_registered;
    private String affiliation;
    private List<String> media_types;
    private List<String> content_types;

    public DockerRepositoryResponse(String name, String namespace, String repository_type, int status, String status_description, String description, boolean is_private, int star_count, int pull_count, String last_updated, String date_registered, String affiliation, List<String> media_types, List<String> content_types) {
        this.name = name;
        this.namespace = namespace;
        this.repository_type = repository_type;
        this.status = status;
        this.status_description = status_description;
        this.description = description;
        this.is_private = is_private;
        this.star_count = star_count;
        this.pull_count = pull_count;
        this.last_updated = last_updated;
        this.date_registered = date_registered;
        this.affiliation = affiliation;
        this.media_types = media_types;
        this.content_types = content_types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getRepository_type() {
        return repository_type;
    }

    public void setRepository_type(String repository_type) {
        this.repository_type = repository_type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatus_description() {
        return status_description;
    }

    public void setStatus_description(String status_description) {
        this.status_description = status_description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIs_private() {
        return is_private;
    }

    public void setIs_private(boolean is_private) {
        this.is_private = is_private;
    }

    public int getStar_count() {
        return star_count;
    }

    public void setStar_count(int star_count) {
        this.star_count = star_count;
    }

    public int getPull_count() {
        return pull_count;
    }

    public void setPull_count(int pull_count) {
        this.pull_count = pull_count;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public String getDate_registered() {
        return date_registered;
    }

    public void setDate_registered(String date_registered) {
        this.date_registered = date_registered;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public List<String> getMedia_types() {
        return media_types;
    }

    public void setMedia_types(List<String> media_types) {
        this.media_types = media_types;
    }

    public List<String> getContent_types() {
        return content_types;
    }

    public void setContent_types(List<String> content_types) {
        this.content_types = content_types;
    }
}

