package hello.login.domain.member;

import ch.qos.logback.core.rolling.DefaultTimeBasedFileNamingAndTriggeringPolicy;
import com.fasterxml.classmate.MemberResolver;
import hello.login.web.member.Member;
import hello.login.web.member.MemberRepository;
import java.rmi.MarshalException;
import java.security.KeyStore.PrivateKeyEntry;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingErrorProcessor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

  private final MemberRepository repository;

  @GetMapping("/add")
  public String addForm(@ModelAttribute("member") Member member) {

    return "members/addForm";
  }

  @PostMapping("/add")
  public String add(@Validated @ModelAttribute("member") Member member,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      bindingResult.reject("addError", "회원가입 오류 발생");
      return "members/addForm";
    }
    repository.save(member);
    return "redirect:/";
  }


}
