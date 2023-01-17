package test.testspring.controller;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {


    @GetMapping("hello")
    public String hello(Model model) {


        model.addAttribute("data", "this is data");

        return "hello";
    }

    @ResponseBody
    @GetMapping("getHello")
    public Hello getHello(@RequestParam("name") String  name) {

        Hello hello = new Hello();
        hello.setName(name);


        return hello;
    }

    @Data
    static class Hello {

        private String name;

    }


}
