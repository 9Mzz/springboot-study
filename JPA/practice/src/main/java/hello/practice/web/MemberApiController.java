package hello.practice.web;

import hello.practice.domain.Member;
import hello.practice.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/member/v1/members")
    public MemberResponse saveMemberV2(@RequestBody MemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        Member memberResult = memberService.memberSave(member);

        return new MemberResponse(memberResult.getId(), memberResult.getName());
    }

    @Data
    static class MemberRequest {
        private String name;
    }

    @Data
    static class MemberResponse {
        private Long   id;
        private String name;
        //

        public MemberResponse() {
        }

        public MemberResponse(Long id, String name) {
            this.id   = id;
            this.name = name;
        }
    }

    @PostMapping("/member/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id, @RequestBody MemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        memberService.UpdateMember(id, member);

        Member findMember = memberService.findById(id);
        log.info("findMember = {}", findMember);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    @Data
    static class UpdateMemberResponse {
        private Long   id;
        private String name;

        public UpdateMemberResponse() {

        }

        public UpdateMemberResponse(Long id, String name) {
            this.id   = id;
            this.name = name;
        }
    }


}
