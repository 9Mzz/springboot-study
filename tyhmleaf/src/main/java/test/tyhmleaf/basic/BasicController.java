package test.tyhmleaf.basic;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.monitor.StringMonitor;
import javax.servlet.http.HttpSession;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String texxBasic(Model model) {
        model.addAttribute("data", "hello!!");
        return "/basic/text-basic";
    }

    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model) {

        model.addAttribute("data", "Hello <b>Spring!</b>");

        return "/basic/text-unescape";
    }

    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User("userA", 25);
        User userB = new User("userB", 52);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);
        model.addAttribute("users", list);


        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);
        model.addAttribute("userMap", map);

        model.addAttribute("user", userA);


        return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session) {
        session.setAttribute("sessionData", "Hello Session");
        return "basic/basic-objects";
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }

    @Data
    static class User {
        private String username;
        private int    age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }


}
