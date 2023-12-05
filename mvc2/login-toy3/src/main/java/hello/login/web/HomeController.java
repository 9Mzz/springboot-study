package hello.login.web;

import hello.login.domain.login.SessionConst;
import hello.login.web.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
public class HomeController {

/*  @GetMapping("/")
  public String home() {
    return "home";
  }*/

  @GetMapping("/")
  public String home(
      @SessionAttribute(name = SessionConst.SESSION_NAME, required = false) Member loginSession,
      Model model) {
    log.info("session = {}", loginSession);
    if (loginSession == null) {
      return "home";
    }

    model.addAttribute("member", loginSession);

    return "loginHome";
  }


}