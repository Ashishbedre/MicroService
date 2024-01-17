package microService.example.microService.dto;

import lombok.Data;

import java.util.List;

@Data
public class ImageTransfer {

    private  String repo ;

    private List<String> tag;
}
