package hello.login.web.login;

import hello.login.domain.login.Login;
import hello.login.domain.login.LoginService;
import hello.login.web.member.Member;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
@RequiredArgsConstructor
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
      bindingResult.reject("loginError", "로그인 오류");
      return "login/loginForm";
    }

    Member member = loginService.loginAct(login.getLoginId(), login.getPassword());
    if (member == null) {
      bindingResult.reject("loginError2", "로그인 오류");
      return "login/loginForm";
    }

    Cookie mySessionCookie = new Cookie("memberId", String.valueOf(member.getId()));
    response.addCookie(mySessionCookie);

    return "redirect:/";
  }

}
