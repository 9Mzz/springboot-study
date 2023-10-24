package hello.login.web.member;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

    return "members/addForm";
  }

  @PostMapping("/add")
  public String add(@Validated @ModelAttribute("member") Member member,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      bindingResult.reject("addError", "회원가입 오류");
      return "members/addForm";
    }

    memberRepository.save(member);

    return "redirect:/";
  }


}
