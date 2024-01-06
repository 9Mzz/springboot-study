package hello.upload03.controller;

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
public class SpringController {

  @Value(value = "${file.route}")
  private String saveDir;

  @GetMapping("/upload")
  public String uploadForm() {
    return "upload-form";
  }

  @PostMapping("/upload")
  public String upload(@RequestParam("itemName") String itemName, @RequestParam("file") MultipartFile file) throws IOException {
    log.info("itemName = {}", itemName);
    log.info("file = {}", file);

    if(!file.isEmpty()) {
      log.info("file getOriginalFilename = {}", file.getOriginalFilename());
      log.info("file getSize = {}", file.getSize());

      String fullPath = saveDir + file.getOriginalFilename();

      file.transferTo(new File(fullPath));

    }

    return "upload-form";
  }

}
