package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.SessionConst;
import hello.login.web.login.Login;
import hello.login.web.login.LoginService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

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
  public String login(@Validated @ModelAttribute("loginForm") Login login,
      BindingResult bindingResult, HttpSession session) {

    if (bindingResult.hasErrors()) {
      return "login/loginForm";
    }

    Member loginMEmber = loginService.loginAct(login.getLoginId(), login.getPassword());
    if (loginMEmber == null) {
      bindingResult.reject("loginError", "아이디 또는 비밀번호가 잘못되었습니다.");
      return "login/loginForm";
    }
    log.info("login Id  = {}", loginMEmber);
    session.setAttribute(SessionConst.SESSION_NAME, loginMEmber);

    return "redirect:/";
  }

  @PostMapping("/logout")
  public String logout(HttpSession session) {

    session.invalidate();

    return "redirect:/";
  }

}
