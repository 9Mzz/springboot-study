package hello.newproject.controller;

import hello.newproject.repository.MemberRepsitory;
import hello.newproject.vo.Member;
import hello.newproject.vo.MemberAddVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

  private final MemberRepsitory memberRepsitory;

  @GetMapping("/add")
  public String memberAddForm(@ModelAttribute("member") Member member) {

    return "members/addMemberForm";
  }

  @PostMapping("/add")
  public String memberAdd(@ModelAttribute("member") MemberAddVo memberForm) {
    log.info("넘어온 데이터 = {}", memberForm);

    Member member = new Member();
    member.setLoginId(memberForm.getLoginId());
    member.setPassword(memberForm.getPassword());
    member.setName(memberForm.getName());

    memberRepsitory.save(member);

    return "redirect:/";
  }

}
