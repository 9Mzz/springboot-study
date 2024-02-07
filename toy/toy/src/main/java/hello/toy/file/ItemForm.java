package hello.toy.file;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class ItemForm {

    private Long                id;
    private String              itemName;
    private MultipartFile       attachFile;
    private List<MultipartFile> imageFiles;

}
