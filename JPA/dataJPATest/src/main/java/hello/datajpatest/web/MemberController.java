package hello.datajpatest.web;

import hello.datajpatest.domain.Member;
import hello.datajpatest.domain.dto.MemberDto;
import hello.datajpatest.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/memberV1/{id}")
    public Member findMemberV1(@PathVariable("id") Long id) {
        return memberRepository.findById(id)
                .get();
    }

    @GetMapping("/memberV2/{id}")
    public Member findMemberV2(@PathVariable("id") Member member) {

        return member;
    }

    @GetMapping("/memberV2-1")
    public Page<Member> findMemberV2_1(
            @PageableDefault(size = 5, sort = "memberName", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Member> members = memberRepository.findAll(pageable);
        return members;
    }

    @GetMapping("/memberV3")
    public List<MemberDto> findMemberV3(@PageableDefault(size = 2) Pageable pageable) {

        Page<Member> members = memberRepository.findAll(pageable);
        return members.map(member -> new MemberDto(member.getId(), member.getMemberName(), member.getAge(), member.getTeam()
                        .getTeamName()))
                .getContent();

    }

}
