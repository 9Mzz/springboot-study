package com.example.test.web;

import com.example.test.domain.dto.MemberTeamDto;
import com.example.test.domain.dto.SearchCondition;
import com.example.test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/v1/members")
    public PageImpl<MemberTeamDto> memberV1(SearchCondition condition, Pageable pageable) {
        return memberRepository.findByWhere(condition, pageable);
    }

}
