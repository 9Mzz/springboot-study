package hello.springmvc.basic.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello");
        mav.addObject("data", "hello!");

        log.info("mav = {}", mav);

        return mav;
    }

    @RequestMapping("/response-view-v1-Test")
    public String responseViewV1Proto(Model model) {

        String name = "hello!!!!!";
        model.addAttribute("data", name);

        return "response/hello";
    }


    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");

        log.info("mav = {}", model);

        return "response/hello";
    }

    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");

        log.info("mav = {}", model);

    }


}





