package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {

        model.addAttribute("data", "hello spring!!");

        return "hello";
    }

    //MVC와 템플릿 엔진
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {


        model.addAttribute("data", name);

        return "hello-template";
    }


    @ResponseBody
    @GetMapping("hello-string")
    public String helloString(@RequestParam("name") String name) {


        return "hello " + name;

    }

}
