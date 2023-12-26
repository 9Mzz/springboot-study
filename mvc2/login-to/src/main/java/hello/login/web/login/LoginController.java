package hello.login.web.login;

import hello.login.domain.login.Login;
import hello.login.domain.login.LoginService;
import hello.login.domain.login.SessionConst;
import hello.login.web.member.Member;
import javax.servlet.http.HttpSession;
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
  public String loginForm(@ModelAttribute("loginForm") Login login) {

    return "login/loginForm";
  }

  @PostMapping("/login")
  public String login(@ModelAttribute("loginForm") Login login,
      @RequestParam("URL") String redirctURL, HttpSession session) {

    Member member = loginService.loginAct(login.getLoginId(), login.getPassword());
    log.info("login data = {}", member);

    session.setAttribute(SessionConst.SESSION_NAME, member);

    return "redirect:" + redirctURL;
  }

  @PostMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }

}
