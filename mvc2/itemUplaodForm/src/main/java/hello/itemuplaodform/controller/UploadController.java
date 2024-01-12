package hello.itemuplaodform.controller;

import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping("/spring")
public class UploadController {

  @Value(value = "${file.route}")
  private String fileDir;

  @GetMapping("/upload")
  public String springUploadForm() {
    return "upload-form";
  }

  @PostMapping("/upload")
  public String springUpload(@RequestParam("itemName") String itemName, @RequestParam("file") MultipartFile file) throws IOException {
    log.info("itemName = {}", itemName);
    log.info("file = {}", file);

    String originalFilename = file.getOriginalFilename();
    String fullPath         = fileDir + originalFilename;

    file.transferTo(new File(fullPath));

    return "upload-form";
  }

}
