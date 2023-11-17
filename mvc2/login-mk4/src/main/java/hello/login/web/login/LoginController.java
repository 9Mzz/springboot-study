package hello.login.web.login;

import hello.login.domain.login.Login;
import hello.login.domain.login.LoginService;
import hello.login.web.member.Member;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

  private final LoginService loginService;


  @GetMapping("/login")
  public String loginForm(@ModelAttribute("loginForm") Login login) {

    return "login/loginForm";
  }

  @PostMapping("/login")
  public String login(@Validated @ModelAttribute("loginForm") Login login,
      BindingResult bindingResult, HttpServletResponse response) {

    if (login == null) {
      return "login/loginForm";
    }

    Member member = loginService.loginAct(login.getLoginId(), login.getPassword());
    log.info("login data = {}", member);
    if (member == null) {
      bindingResult.reject("loginError", "아이디 또는 비밀번호가 잘못되었습니다.");
      return "login/loginForm";
    }

    Cookie mySessionCookie = new Cookie("memberId", String.valueOf(member.getId()));
    response.addCookie(mySessionCookie);

    return "redirect:/";
  }

  @PostMapping("/logout")
  public String logOut(HttpServletResponse response) {
    Cookie mySessionCookie = new Cookie("memberId", null);
    mySessionCookie.setMaxAge(0);
    response.addCookie(mySessionCookie);

    return "redirect:/";
  }

}
