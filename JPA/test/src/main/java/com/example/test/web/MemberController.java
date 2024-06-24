package com.example.test.web;

import com.example.test.domain.Member;
import com.example.test.domain.dto.MemberCaseDto;
import com.example.test.domain.dto.MemberTeamDto;
import com.example.test.domain.dto.SearchCondition;
import com.example.test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;


    @GetMapping("/v1/{ageParam}")
    public String bulkV1(@PathVariable(value = "ageParam", required = false) Integer ageParam) {
        int result = memberRepository.bulkUpdate(ageParam);
        return String.valueOf(result);
    }

    @GetMapping("/v2")
    public List<MemberCaseDto> memberV1(@RequestParam("ageParam") int ageParam) {

        return memberRepository.caseBuilder(ageParam);
    }

    @GetMapping("/v3")
    public List<Member> memberV2() {
        return memberRepository.jpaExpressionPractice();
    }

    @GetMapping("/v4")
    public Page<MemberTeamDto> memberV3(SearchCondition condition, Pageable pageable) {

        log.info("condition = {}", condition);

        return memberRepository.memberPagingTest(condition, pageable);
    }

}
