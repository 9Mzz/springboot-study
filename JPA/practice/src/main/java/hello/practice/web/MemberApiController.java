package hello.practice.web;

import hello.practice.domain.Member;
import hello.practice.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 조회 V1 : 응답 값으로 엔티티를 직접 외부에 노출한다.
     */
    @GetMapping("/api/v1/members")
    public List<Member> membersV1() {
        return memberService.findAll();
    }

    /**
     * 등록 V1: 요청 값으로 Member 엔티티를 직접 받는다.
     */
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody Member member) {
        Member saveMember = memberService.memberSave(member);
        return new CreateMemberResponse(saveMember.getId());
    }

    @GetMapping("/api/v2/members")
    public Result membersV2() {
        List<Member> members = memberService.findAll();
        List<MemberDto> collect = members.stream()
                .map(member -> new MemberDto(member.getName()))
                .toList();
        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T   data;

        public Result(T data) {
            this.data = data;
        }
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }

    /**
     * 등록 V2: 요청 값으로 Member 엔티티 대신에 별도의 DTO를 받는다.
     */
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody CreateMemberRequest request) {

        Member member = new Member();
        member.setName(request.getName());

        Member saveMember = memberService.memberSave(member);
        return new CreateMemberResponse(saveMember.getId());
    }


    @Data
    static class CreateMemberRequest {
        private String name;
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }


    @PostMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id, @RequestBody UpdateMemberDto request) {
        Member member = new Member();
        member.setName(request.getMemberName());
        log.info("member = {}" + member);

        memberService.UpdateMember(id, member);
        Member findMember = memberService.findById(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    @Data
    static class UpdateMemberDto {
        private String memberName;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long   id;
        private String memberName;
    }

}
