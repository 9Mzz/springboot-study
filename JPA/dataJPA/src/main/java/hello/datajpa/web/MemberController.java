package hello.datajpa.web;

import hello.datajpa.domain.Member;
import hello.datajpa.domain.dto.MemberDto;
import hello.datajpa.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;


    @GetMapping("/membersV1/{id}")
    public Optional<Member> findMemberV1(@PathVariable("id") Long id) {
        Optional<Member> byId = memberRepository.findById(id);
        log.info("byId = {}", byId);
        return byId;
    }

    @GetMapping("/membersV2/{id}")
    public Member findMemberV2(@PathVariable("id") Member member) {
        return member;
    }

    @GetMapping("/membersV3")
    public Page<Member> findMemberV3(Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable);
        return page;
    }

    /**
     * 둘 이상의 페이징 정보를 출력해야 되는 경우는 접두사로 구분할 수 있다
     *
     * @Qualifier 어노테이션에 접두사명 추가 "접두사명_xxx"
     * @Qualifier("member") Pageable memberPageable,
     * @Qualifier("team") Pageable teamPageable
     * /members?member_page=0&order_page=1
     */
    @RequestMapping(value = "/memberV3_page", method = RequestMethod.GET)
    public String pageAnnotation(
            @PageableDefault(size = 3, sort = "age", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable);
        return page.getContent()
                .toString();
    }

    @GetMapping("/membersV4")
    public List<MemberDto> findMemberV4(Pageable pageable) {
        return memberRepository.findAll(pageable)
                .map(member -> new MemberDto(member.getId(), member.getUserName(), member.getAge(), member.getTeam()
                        .getName()))
                .getContent();
    }


}
