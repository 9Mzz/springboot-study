package hello.toyex.toy;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/toy")
public class ToyController {

    @GetMapping("/formatter-form")
    public String formatterForm(Model model) {
        fForm fForm = new fForm(10000, LocalDateTime.now());
        model.addAttribute("form", fForm);

        return "converter/formatter-form";
    }

    @PostMapping("/formatter-form")
    public String formatterResult(@ModelAttribute("form") fForm form) {

        return "converter/formatter-view";
    }

    @GetMapping("/formatter-view")
    public String formaatterView(Model model) {
        fForm fForm = new fForm(10000, LocalDateTime.now());
        model.addAttribute("form", fForm);
        return "converter/formatter-view";
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class fForm {
        private Integer       number;
        private LocalDateTime localDateTime;

    }

    @GetMapping("/converter-form")
    public String converterForm(@ModelAttribute("form") cForm form) {
        form.setIpPort(new IpPort("127.0.0.1", 8080));
        return "converter/converter-form";
    }

    @PostMapping("/converter-form")
    public String converterEdit(@ModelAttribute("form") cForm form, Model model) {
        model.addAttribute("number", 10000);
        model.addAttribute("ipPort", form.getIpPort());
        return "converter/converter-view";
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    static class cForm {

        private IpPort ipPort;

        public cForm(IpPort ipPort) {
            this.ipPort = ipPort;
        }

    }


}
