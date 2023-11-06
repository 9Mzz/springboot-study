package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
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
  public String home() {
    return "home";
  }

  @GetMapping("/")
  public String cookieHome(@CookieValue(name = "memberId", required = false) Long memberId,
      Model model) {

    if (memberId == null) {
      return "home";
    }

    Member member = memberRepository.findById(memberId);
    model.addAttribute("member", member);

    return "cookieHome";
  }
}