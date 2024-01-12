package hello.totalproject.converter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ConvertController {

  @GetMapping("/converter/view")
  public String converterView(Model model) {
    model.addAttribute("number", 100000);
    model.addAttribute("ipPort", new IpPort("127.0.0.1", 8080));

    return "converter-view";
  }

  @GetMapping("/converter/form")
  public String converterForm(Model model) {

    IpPort ipPort = new IpPort("127.0.0.1", 8080);
    Form   form   = new Form(ipPort);
    model.addAttribute("form", form);

    return "converter-form";
  }

  @PostMapping("/converter/form")
  public String converterEdit(@ModelAttribute("form") Form form, Model model) {
    IpPort ipPort = form.getIpPort();
    model.addAttribute("ipPort", ipPort);
    return "converter-view";
  }

  @Getter
  @Setter
  @RequiredArgsConstructor
  static class Form {


    private IpPort ipPort;

    public Form(IpPort ipPort) {
      this.ipPort = ipPort;
    }
  }


}
