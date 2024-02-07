package hello.toy.login;

import hello.toy.member.Member;
import hello.toy.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
                           HttpSession session) {
        Member member = loginService.LoginAct(login.getLoginId(), login.getPassword());
        if(member == null) {
            bindingResult.reject("loginError", "아이디 또는 비밀번호가 잘못되었습니다.");
        }
        if(bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        session.setAttribute(SessionConst.SESSION_NAME, member);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
