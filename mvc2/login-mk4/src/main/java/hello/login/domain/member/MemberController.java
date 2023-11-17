package hello.login.domain.member;

import hello.login.web.member.Member;
import hello.login.web.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

  private final MemberRepository memberRepository;

  @GetMapping("/add")
  public String addForm(@ModelAttribute("member") Member member) {

    return "members/addMemberForm";
  }

  @PostMapping("/add")
  public String add(@ModelAttribute("member") Member member) {
    memberRepository.save(member);
    return "redirect:/";
  }

}
