package hello.newproject.controller;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/format")
@RequiredArgsConstructor
public class FormatterController {

  @GetMapping("/formatter-view")
  public String formatterView(Model model) {
    Form form = new Form();
    form.setNumber(1000);
    form.setLocalDateTime(LocalDateTime.now());
    model.addAttribute("form", form);
    return "formatter-view";
  }

  @GetMapping("/formatter-form")
  public String formatterForm(Model model) {
    Form form = new Form();
    form.setNumber(1000);
    form.setLocalDateTime(LocalDateTime.now());
    model.addAttribute("form", form);
    return "formatter-form";
  }

  @PostMapping("/formatter-form")
  public String formatterEdit(@ModelAttribute("form") Form formParam) {

    return "formatter-view";
  }

  @Data
  static class Form {

    @NumberFormat(pattern = "###,###원")
    private Integer       number;
    @DateTimeFormat(pattern = "yy:MM:dd HH:mm:ss")
    private LocalDateTime localDateTime;
  }

}
