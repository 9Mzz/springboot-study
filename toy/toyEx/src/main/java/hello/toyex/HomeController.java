package hello.toyex;

import hello.toyex.members.Members;
import hello.toyex.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
public class HomeController {

    @GetMapping
    public String home(@SessionAttribute(value = SessionConst.SESSION_NAME, required = false) Members sessionMember,
                       Model model) {
        if(sessionMember == null) {
            return "home";
        }
        model.addAttribute("member", sessionMember);
        return "loginHome";
    }

}
