package hello.login.domain.member;


import hello.login.web.member.Member;
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
@RequestMapping("/members")
public class MemberController {

  private final MemberRepository memberRepository;

  @GetMapping("/add")
  public String addForm(@ModelAttribute("member") Member member) {

    return "/members/addForm";
  }

  @PostMapping("/add")
  public String add(@Validated @ModelAttribute("member") Member member,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      log.info("error 발생 = {}", bindingResult);
      return "/members/addForm";
    }

    memberRepository.save(member);

    return "redirect:/";
  }


}