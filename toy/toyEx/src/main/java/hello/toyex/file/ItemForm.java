package hello.toyex.file;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class ItemForm {

    private long                id;
    private String              itemName;
    private MultipartFile       attachFile;
    private List<MultipartFile> imageFiles;

    public ItemForm(String itemName, MultipartFile attachFile, List<MultipartFile> imageFiles) {
        this.itemName = itemName;
        this.attachFile = attachFile;
        this.imageFiles = imageFiles;
    }
}
