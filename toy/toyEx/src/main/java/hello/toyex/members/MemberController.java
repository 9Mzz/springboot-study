package hello.toyex.members;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;


    @GetMapping("/add")
    public String memberAddForm(@ModelAttribute("member") Members members) {

        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String memberAdd(@Validated @ModelAttribute("member") Members members, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            log.info("binding error = {}", bindingResult.getAllErrors());
            return "members/addMemberForm";
        }

        memberRepository.save(members);
        return "redirect:/";
    }

}
