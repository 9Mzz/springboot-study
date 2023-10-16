package hello.login.web.login;


import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class LoginController {


  private final LoginService loginService;

  @GetMapping("/login")
  private String loginForm(@ModelAttribute("loginForm") LoginForm form) {

    return "login/loginForm";
  }

  @PostMapping("/login")
  private String login(@Validated @ModelAttribute("loginForm") LoginForm form,
      BindingResult bindingResult, HttpServletResponse response) {

    if (bindingResult.hasErrors()) {
      log.info("error code = {}", bindingResult);
      return "login/loginForm";
    }

    Member loginMember = loginService.loginCheck(form.getLoginId(), form.getPassword());
    if (loginMember == null) {
      bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
      return "login/loginForm";
    }

    //로그인 성공 처리 TODO

    //쿠키에 시간 정보를 주지 않으면 세션 쿠키(브라우저 종료시 모두 종료)
    Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
    response.addCookie(idCookie);

    return "redirect:/";
  }

  @PostMapping("/logout")
  public String logout(HttpServletResponse response) {
    expiredCookie(response, "memberId");
    return "redirect:/";

  }

  private static void expiredCookie(HttpServletResponse response, String cookieName) {
    Cookie cookie = new Cookie(cookieName, null);
    cookie.setMaxAge(0);
    response.addCookie(cookie);
  }

}
