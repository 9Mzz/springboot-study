package hello.login.web.login;


import hello.login.domain.login.Login;
import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
      BindingResult bindingResult, HttpServletResponse response) {

    if (bindingResult.hasErrors()) {
      return "login/loginForm";
    }

    if (login == null) {
      return "login/loginForm";
    }

    Member member = loginService.loginAct(login.getLoginId(), login.getPassword());
    if (member == null) {
      bindingResult.reject("loginError", "아이디 또는 비밀번호가 잘못되었습니다.");
      return "login/loginForm";
    }

    Cookie memberId = new Cookie("memberId", String.valueOf(member.getId()));
    response.addCookie(memberId);
    return "redirect:/";

  }

  @PostMapping("/logout")
  public String logout(HttpServletResponse response) {

    Cookie memberId = new Cookie("memberId", null);
    memberId.setMaxAge(0);
    response.addCookie(memberId);

    return "redirect:/";
  }


}
