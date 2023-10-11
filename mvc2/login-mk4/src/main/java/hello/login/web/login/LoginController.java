package hello.login.web.login;


import hello.login.domain.login.LoginForm;
import hello.login.domain.login.LoginService;
import hello.login.web.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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
  public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
    return "login/loginForm";
  }

  @PostMapping("/login")
  public String login(@Validated @ModelAttribute("loginForm") LoginForm form,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      log.info("error code = {}", bindingResult);
      return "login/loginForm";
    }

    Member loginResult = loginService.loginAct(form.getLoginId(), form.getPassword());

    if (loginResult == null) {
      bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
      log.info("error code = {}", bindingResult);
      return "login/loginForm";
    }

    return "redirect:/";
  }


}
