package hello.login.web;

import hello.login.web.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.MethodOverride;
import org.springframework.boot.Banner.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
public class HomeController {

  //  @GetMapping("/")
  public String home() {
    return "home";
  }

  @GetMapping("/")
  public String sessionHome(
      @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
      Model model) {

    if (loginMember == null) {
      return "home";
    }

    model.addAttribute("member", loginMember);

    return "loginHome";
  }
}