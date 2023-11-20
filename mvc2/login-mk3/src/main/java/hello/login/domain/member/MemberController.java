package hello.login.domain.member;

import hello.login.web.member.Member;
import hello.login.web.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

  private final MemberRepository memberRepository;

  @GetMapping("/add")
  public String addForm(@ModelAttribute("member") Member member) {

    return "members/addMemberForm";
  }

  @PostMapping("/add")
  public String add(@Validated @ModelAttribute("member") Member member,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      bindingResult.reject("memberAddForm", "회원가입 오류");
      return "members/addMemberForm";
    }

    memberRepository.save(member);

    return "redirect:/";
  }

}
