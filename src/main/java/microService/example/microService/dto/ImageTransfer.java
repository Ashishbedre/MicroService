package microService.example.microService.dto;

import lombok.Data;

import java.util.List;

@Data
public class ImageTransfer {

    private  String product ;

    private List<Version> versions;
}
