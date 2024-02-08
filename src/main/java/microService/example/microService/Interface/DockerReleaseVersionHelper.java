package microService.example.microService.Interface;

import microService.example.microService.Entity.ProductList;

import java.time.LocalDateTime;

public interface DockerReleaseVersionHelper {

    public LocalDateTime dateTimeConverter(String inputDateTimeString);
    public int pullCount(String repositoriesName);
    public void saveLastPull();
    public  String updateLastPull(ProductList productList);

}
