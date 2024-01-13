package hello.fileupload.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {

  private Long             id;
  private String           itemName;
  private UplaodFile       attachFile;
  private List<UplaodFile> imageFiles;

  public Item() {
  }

  public Item(String itemName, UplaodFile attachFile, List<UplaodFile> imageFiles) {
    this.itemName = itemName;
    this.attachFile = attachFile;
    this.imageFiles = imageFiles;
  }
}
