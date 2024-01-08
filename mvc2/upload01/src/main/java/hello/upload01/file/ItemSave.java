package hello.upload01.file;

import lombok.Data;

@Data
public class ItemSave {

  private String uploadFileName;
  private String storeFileName;

  public ItemSave(String uploadFileName, String storeFileName) {
    this.uploadFileName = uploadFileName;
    this.storeFileName = storeFileName;
  }
}
