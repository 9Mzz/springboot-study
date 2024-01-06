package hello.upload03.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ItemController {

  @GetMapping("/items/new")
  public String itemUploadForm() {

    return "item-form";
  }

  @PostMapping("/items/new")
  public String itemUpload() {

    return "item-form";
  }

}
