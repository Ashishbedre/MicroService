package microService.example.microService.Repository;

import microService.example.microService.Entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,Long> {
    Optional<Image> findByRepoAndTag(String repo, String tag);
    @Query("SELECT i FROM Image i WHERE i.tag = :tagName AND i.repo = :repoName")
    Image findImageByTagAndRepo(@Param("repoName") String repoName,@Param("tagName") String tagName);

    @Query("SELECT i FROM Image i WHERE i.repo = :repoName AND i.id < :imageId ORDER BY i.id ASC")
    List<Image> findImagesAboveIdByRepo(@Param("repoName") String repoName, @Param("imageId") Long imageId);
    @Query("SELECT i FROM Image i WHERE i.repo = :repoName AND i.id > :imageId ORDER BY i.id ASC")
    List<Image> findImagesBelowIdByRepo(@Param("repoName") String repoName, @Param("imageId") Long imageId);

}
