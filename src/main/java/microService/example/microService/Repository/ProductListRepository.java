package microService.example.microService.Repository;

import jakarta.transaction.Transactional;
import microService.example.microService.Entity.ProductList;
import org.springframework.data.domain.Pageable;
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

//    upgrade and downgrade
    @Query("SELECT p.id FROM ProductList p WHERE p.product = :product AND p.version = :version")
    Long findIdByProductAndVersion(@Param("product") String product, @Param("version") String version);

    @Query("SELECT p FROM ProductList p WHERE p.product = :product AND p.id > :imageId ORDER BY p.id ASC")
    List<ProductList> findAllDataByProductAndIdLessThanOrderByidAsc(String product, Long imageId);

    @Query("SELECT p FROM ProductList p WHERE p.product = :product AND p.id < :imageId ORDER BY p.id ASC")
    List<ProductList> findAllDataByProductAndIdMoreThanOrderByidAsc(String product, Long imageId, Pageable pageable);

//    latest verion
    @Query("SELECT p FROM ProductList p WHERE p.id IN (SELECT MAX(p2.id) FROM ProductList p2 GROUP BY p2.product)")
    List<ProductList> findLatestVersionsOfProducts();

}
