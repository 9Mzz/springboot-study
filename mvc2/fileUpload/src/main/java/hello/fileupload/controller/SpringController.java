package hello.fileupload.controller;


import java.io.File;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping("/spring")
public class SpringController {

  @Value(value = "${file.dir}")
  private String fileDir;

  @GetMapping("/upload")
  public String springUplaodForm(@ModelAttribute("form") Form form) {
    return "upload-form";
  }

  @PostMapping("/upload")
  public String springUplaod(@ModelAttribute("form") Form form) throws IOException {
    log.info("form = {}", form);

    String originalFilename = form.getFile()
                                  .getOriginalFilename();
    String fullPath = fileDir + originalFilename;
    form.getFile()
        .transferTo(new File(fullPath));

    return "upload-form";
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  static class Form {

    private String        itemName;
    private MultipartFile file;


  }

}
