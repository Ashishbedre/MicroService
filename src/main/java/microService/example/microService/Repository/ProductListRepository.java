package microService.example.microService.Repository;

import jakarta.transaction.Transactional;
import microService.example.microService.Entity.ProductList;
import microService.example.microService.Interface.ProductListDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductListRepository  extends JpaRepository<ProductList,Long> {

    @Query("SELECT p.version FROM ProductList p WHERE p.product = :productName")
    List<String> findVersionsByProduct(String productName);

    @Query("SELECT p.product FROM ProductList p GROUP BY p.product")
    List<String> findDistinctProductNames();

    List<ProductList> findByProduct(String productName);

    @Transactional
    @Modifying
    @Query("DELETE FROM ProductList p WHERE p.product = :productName AND p.version = :version")
    void deleteByProductAndVersion(@Param("productName") String productName, @Param("version") String version);
}
