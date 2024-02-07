package hello.toy.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private Long             id;
    private String           itemName;
    private UploadFile       attachFile;
    private List<UploadFile> imageFiles;
}
