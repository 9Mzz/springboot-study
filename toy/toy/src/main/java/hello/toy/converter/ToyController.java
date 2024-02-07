package hello.toy.converter;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
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
        fForm fForm = new fForm();
        fForm.setNumber(10000);
        fForm.setLocalDateTime(LocalDateTime.now());
        model.addAttribute("form", fForm);
        return "converter/formatter-form";
    }

    @PostMapping("/formatter-form")
    public String formatterEdit(@ModelAttribute("form") fForm fForm) {
        return "converter/formatter-view";
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class fForm {
        private Integer       number;
        @DateTimeFormat(pattern = "yy:MM:dd HH:mm:ss")
        private LocalDateTime localDateTime;
    }

    @GetMapping("/converter-form")
    public String converter_form(Model model) {

        cForm cForm = new cForm();
        cForm.setIpPort(new IpPort("127.0.0.1", 8080));
        model.addAttribute("form", cForm);

        return "converter/converter-form";
    }

    @PostMapping("/converter-form")
    public String converter_submit(@ModelAttribute("form") cForm cForm, Model model) {
        IpPort ipPort = cForm.getIpPort();
        model.addAttribute("number", 10000);
        model.addAttribute("ipPort", ipPort);
        return "converter/converter-view";
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    static class cForm {
        private Integer number;
        private IpPort  ipPort;

        public cForm(IpPort port) {
            this.ipPort = port;
        }
    }
}
