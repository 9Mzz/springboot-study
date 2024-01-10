package hello.newproject.controller;

import hello.newproject.service.LoginService;
import hello.newproject.vo.LoginVo;
import hello.newproject.vo.Member;
import hello.newproject.vo.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

  private final LoginService loginService;

  @GetMapping("/login")
  public String loginForm(@ModelAttribute("loginForm") LoginVo loginVo) {

    return "login/loginForm";
  }

  @PostMapping("/login")
  public String login(@ModelAttribute("loginForm") LoginVo loginVo,
      @RequestParam("requestURL") String requestURL,
      HttpSession session) {
    Member member = loginService.loginAct(loginVo.getLoginId(), loginVo.getPassword());
    session.setAttribute(SessionConst.SESSION_NAME, member);

    return "redirect:" + requestURL;
  }

  @PostMapping("/logout")
  public String logout(HttpSession session) {

    session.invalidate();

    return "redirect:/";
  }

}
