package com.example.test.web;

import com.example.test.domain.Member;
import com.example.test.domain.dto.SearchCondition;
import com.example.test.domain.dto.testV1Dto;
import com.example.test.domain.dto.testV4Dto;
import com.example.test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/v1")
    public List<testV1Dto> testV1() {
        return memberRepository.testV1();
    }

    @GetMapping("/v2")
    public List<Member> testV2() {
        return memberRepository.testV2();
    }

    @GetMapping("/v3")
    public List<Member> testV3() {
        return memberRepository.testV3();
    }

    @GetMapping("/v4")
    public List<testV4Dto> testV4() {
        return memberRepository.testV4();
    }

    @GetMapping("/v5")
    public PageImpl<testV1Dto> testV5(SearchCondition condition, Pageable pageable) {
        return memberRepository.testV5(condition, pageable);
    }


}
