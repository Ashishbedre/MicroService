package microService.example.microService.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ProductList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product;

    private String version ;

    private String changeLog;

    private String knowFix;

    private LocalDateTime lastPull;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }

    public String getKnowFix() {
        return knowFix;
    }

    public void setKnowFix(String knowFix) {
        this.knowFix = knowFix;
    }

    public LocalDateTime getLastPull() {
        return lastPull;
    }

    public void setLastPull(LocalDateTime lastPull) {
        this.lastPull = lastPull;
    }
}
