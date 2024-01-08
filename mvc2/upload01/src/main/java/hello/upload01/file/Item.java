package hello.upload01.file;

import java.util.List;
import lombok.Data;

@Data
public class Item {

  private Long           id;
  private String         itemName;
  private ItemSave       attachFile;
  private List<ItemSave> imageFiles;

}
