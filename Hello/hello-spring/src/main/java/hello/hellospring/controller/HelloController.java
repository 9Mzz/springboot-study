package hello.hellospring.controller;


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


    //@ResponseBody 객체 반환
    @ResponseBody
    @GetMapping("hello-api")
    public Hello helloApi(@RequestParam("name") String name) {

        Hello hello = new Hello();

        hello.setName(name);

        //@ResponseBody 를 사용하고, 객체를 반환하면 객체가 JSON으로 변환됨

        return hello;
    }

    static class Hello {

        private String name;

        public Hello() {

        }

        public Hello(String name) {
            this.name = name;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Hello{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }


}
