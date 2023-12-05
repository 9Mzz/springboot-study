package hello.login.web.login;

import hello.login.domain.login.Login;
import hello.login.domain.login.LoginService;
import hello.login.web.SessionConst;
import hello.login.web.member.Member;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    return "/login/loginForm";
  }

  @PostMapping("/login")
  public String login(@Validated @ModelAttribute("loginForm") Login login,
      BindingResult bindingResult,
      HttpSession session) {

    if (bindingResult.hasErrors()) {
      return "/login/loginForm";
    }

    Member result = loginService.loginAct(login.getLoginId(), login.getPassword());

    if (result == null) {
      bindingResult.reject("loginError", "아이디 또는 비밀번호가 잘못되었습니다.");
      return "/login/loginForm";
    }
    session.setAttribute(SessionConst.SESSION_NAME, result);

    return "redirect:";
  }

  @PostMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();

    return "redirect:/";
  }

}
