package hello.totalproject.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
public class MemberController {

  private final MemberRepository memberRepository;

  @GetMapping("/members/add")
  public String memberAddForm(@ModelAttribute("member") Member member) {
    return "members/addMemberForm";
  }

  @PostMapping("/members/add")
  public String memberAdd(@ModelAttribute("member") Member memberForm) {

    Member member = memberRepository.save(memberForm);

    return "redirect:/";
  }

}
