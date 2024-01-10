package hello.newproject.controller;

import hello.newproject.vo.Member;
import hello.newproject.vo.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequestMapping()
public class HomeController {

  @GetMapping("/")
  public String home(@SessionAttribute(name = SessionConst.SESSION_NAME, required = false) Member member,
      Model model) {

    if(member == null) {
      log.info("session 없음");
      return "home";
    }

    log.info("session member = {}", member);
    model.addAttribute("member", member);

    return "loginHome";
  }
}
