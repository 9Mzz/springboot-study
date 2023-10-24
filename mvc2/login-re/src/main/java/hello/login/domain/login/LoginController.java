package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.web.login.Login;
import hello.login.web.login.LoginService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
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
    if (bindingResult.hasErrors()) {
      log.info("login Error", bindingResult);
      return "login/loginForm";
    }

    Member loginResult = loginService.loginAct(login.getLoginId(), login.getPassword());
    log.info("login result = {}", loginResult);
    if (loginResult == null) {
      bindingResult.rejectValue("loginError", "로그인 오류 발생");
    }

    Cookie memberId = new Cookie("memberId", String.valueOf(loginResult.getId()));
    response.addCookie(memberId);

    return "redirect:/";
  }

}
