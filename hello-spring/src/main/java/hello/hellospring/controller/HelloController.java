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

        //@ResponseBody 를 사용하면 뷰 리졸버(viewResolver)를 사용하지 않음
        //대신에 HTTP의 BODY에 문자 내용을 직접 반환(HTML BODY TAG를 말하는 것이 아님)

        return "hello " + name;

    }

}
