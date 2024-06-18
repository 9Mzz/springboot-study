package hello.querydsl.web;

import hello.querydsl.domain.condition.MemberSearchCondition;
import hello.querydsl.domain.dto.MemberTeamDto;
import hello.querydsl.repository.MemberRepositoryV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepositoryV1 memberRepositoryV1;

    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMemberV1(MemberSearchCondition condition) {
        return memberRepositoryV1.searchByBuilder(condition);
    }

}
