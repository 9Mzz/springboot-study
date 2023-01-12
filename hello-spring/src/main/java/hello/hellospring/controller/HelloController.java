package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){

        model.addAttribute("data", "hello spring!!");

        return "hello";
    }

    // 정적 컨텐츠
    @GetMapping("hello-static")
    public String hellost(){


        return "hello-static";
    }

}
