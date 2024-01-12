package hello.itemuplaodform.form;

import java.util.List;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ItemsForm {

  private Long                id;
  private String              itemName;
  private MultipartFile       attachFile;
  private List<MultipartFile> imageFiles;


}
