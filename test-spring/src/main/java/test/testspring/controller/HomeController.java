package test.testspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import test.testspring.domain.Member;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }




}