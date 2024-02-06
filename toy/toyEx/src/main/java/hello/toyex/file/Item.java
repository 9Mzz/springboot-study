package hello.toyex.file;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Item {

    private Long             id;
    private String           itemName;
    private ItemUpload       attachFile;
    private List<ItemUpload> imageFiles;

    public Item(String itemName, ItemUpload attachFile, List<ItemUpload> imageFiles) {
        this.itemName = itemName;
        this.attachFile = attachFile;
        this.imageFiles = imageFiles;
    }
}
