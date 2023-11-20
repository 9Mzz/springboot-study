package hello.login.web;

import hello.login.web.member.Member;
import hello.login.web.member.MemberRepository;
import javax.swing.RootPaneContainer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

  private final MemberRepository memberRepository;

//  @GetMapping("/")
//  public String home() {
//    return "home";
//  }


  @GetMapping("/")
  public String cookieHome(@CookieValue(name = "memberId", required = false) Long cookieId,
      Model model) {

    if (cookieId == null) {
      return "home";
    }
    Member member = memberRepository.findbyId(cookieId);
    if (member == null) {
      return "home";
    }

    model.addAttribute("member", member);

    return "loginHome";
  }
}