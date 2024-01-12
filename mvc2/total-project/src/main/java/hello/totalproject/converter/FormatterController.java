package hello.totalproject.converter;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/formatter")
@Controller
public class FormatterController {

  @GetMapping("/view")
  public String formatterView(@ModelAttribute("form") Form form) {
    form.setNumber(10000);
    form.setLocalDateTime(LocalDateTime.now());
    return "formatter-view";
  }

  @GetMapping("/form")
  public String formatterForm(Model model) {
    Form form = new Form();
    form.setNumber(100000);
    form.setLocalDateTime(LocalDateTime.now());
    model.addAttribute("form", form);

    return "formatter-form";
  }

  @PostMapping("/form")
  public String formatterEdit(@ModelAttribute("form") Form form) {
    return "formatter-view";
  }

  @Data
  static class Form {

    private Integer       number;
    private LocalDateTime localDateTime;
  }

}
