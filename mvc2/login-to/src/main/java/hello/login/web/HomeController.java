package hello.login.web;

import hello.login.domain.login.SessionConst;
import hello.login.web.member.Member;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
public class HomeController {

  //  @GetMapping("/")
  public String home() {
    return "home";
  }

  @GetMapping("/")
  public String sessionHome(@SessionAttribute(name = SessionConst.SESSION_NAME, required = false)
  Member member, Model model) {
    if (member == null) {
      return "home";
    }
    model.addAttribute("member", member);

    return "loginHome";
  }
}