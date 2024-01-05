package hello.upload.domain;

import jdk.jfr.Description;
import lombok.Data;

@Data
public class UploadFile {

  @Description("고객이 업로드한 파일명")
  private String uploadFileName;
  @Description("서버 내부에서 관리하는 파일명")
  private String storeFileName;

  public UploadFile(String uploadFileName, String storeFileName) {
    this.uploadFileName = uploadFileName;
    this.storeFileName = storeFileName;
  }
}
