package hello.upload.controller;

import jakarta.servlet.http.HttpServletRequest;
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
public class SpringUploadController {

  @Value("${file.dir}")
  private String fileDir;

  @GetMapping("/upload")
  public String newFile() {
    return "upload-form";
  }

  @PostMapping("/upload")
  public String saveFile(@RequestParam("itemName") String itemName,
      @RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

    log.info("itemName = {}", itemName);
    log.info("MultipartFile = {}", file);
    log.info("request = {}", request);

    if (!file.isEmpty()) {
      String fullPath = fileDir + file.getOriginalFilename();
      log.info("파일 저장 fullPath = {}", fullPath);
      //파일을 경로에다가 저장해줌
      file.transferTo(new File(fullPath));
    }

    return "upload-form";
  }

}
