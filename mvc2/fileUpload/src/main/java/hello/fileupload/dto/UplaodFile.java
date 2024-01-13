package hello.fileupload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UplaodFile {

  private String originalFileName;
  private String uploadFileName;

}
