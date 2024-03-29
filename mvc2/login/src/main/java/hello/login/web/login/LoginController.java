package hello.login.web.login;


import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import hello.login.web.session.SessionManager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.SessionIdGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class LoginController {


  private final LoginService   loginService;
  private final SessionManager sessionManager;


  @GetMapping("/login")
  private String loginForm(@ModelAttribute("loginForm") LoginForm form) {

    return "login/loginForm";
  }

  /*
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
    */

  //  @PostMapping("/login")
  private String loginV2(@Validated @ModelAttribute("loginForm") LoginForm form,
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

    //세션 관리자를 통해 세션을 생성하고, 회원 데이터 보관
    sessionManager.createSession(loginMember, response);

    return "redirect:/";
  }

  //  @PostMapping("/login")
  private String loginV3(@Validated @ModelAttribute("loginForm") LoginForm form,
      BindingResult bindingResult, HttpServletRequest request) {

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
    //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
    //세션을 생성하려면 request.getSession(true) 를 사용하면 된다. (default = true)
    HttpSession session = request.getSession();
    //세션에 로그인 회원 정보 보관
    session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

    return "redirect:/";
  }

  @PostMapping("/login")
  private String loginV4(@Validated @ModelAttribute("loginForm") LoginForm form,
      BindingResult bindingResult,
      @RequestParam(defaultValue = "/") String redirectURL,
      HttpServletRequest request) {

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
    //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
    //세션을 생성하려면 request.getSession(true) 를 사용하면 된다. (default = true)
    HttpSession session = request.getSession();
    //세션에 로그인 회원 정보 보관
    session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

    return "redirect:" + redirectURL;
  }

  ///////////////////////////////////////////////////////////////////

  //  @PostMapping("/logout")
  public String logout(HttpServletResponse response) {
    expiredCookie(response, "memberId");
    return "redirect:/";

  }

  //  @PostMapping("/logout")
  public String logoutV2(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }
    return "redirect:/";

  }

  @PostMapping("/logout")
  public String logoutV3(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }

    return "redirect:/";
  }

  private static void expiredCookie(HttpServletResponse response, String cookieName) {
    Cookie cookie = new Cookie(cookieName, null);
    cookie.setMaxAge(0);
    response.addCookie(cookie);
  }

}
