package hello.itemuplaodform.form;

import java.util.List;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Items {

  private Long id;
  private String itemName;
  private UploadFile attachFile;
  private List<UploadFile> imageFiles;

}
