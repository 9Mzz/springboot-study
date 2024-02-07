package hello.toy.main;

import hello.toy.member.Member;
import hello.toy.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequestMapping
public class MainController {

    @GetMapping
    public String main(@SessionAttribute(value = SessionConst.SESSION_NAME, required = false) Member sessionMember,
                       Model model) {

        if(sessionMember == null) {
            return "home";
        }
        model.addAttribute("member", sessionMember);

        return "loginHome";
    }

}
