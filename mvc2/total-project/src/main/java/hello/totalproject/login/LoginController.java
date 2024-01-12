package hello.totalproject.login;

import hello.totalproject.member.Member;
import jakarta.servlet.http.HttpSession;
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
    return "login/loginForm";
  }

  @PostMapping("/login")
  public String loginAct(@Validated @ModelAttribute("loginForm") Login login, BindingResult bindingResult,
      @RequestParam("requestURL") String requestURL,
      HttpSession session) {
    Member member = loginService.LoginAction(login.getLoginId(), login.getPassword());
    if(member == null) {
      bindingResult.reject("loginError", "로그인 오류");
    }
    if(bindingResult.hasErrors()) {
      log.info("error code = {}", bindingResult);
      return "login/loginForm";
    }
    session.setAttribute(SessionConst.SESSION_NAME, member);
    return "redirect:" + requestURL;
  }

  @PostMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }

}
