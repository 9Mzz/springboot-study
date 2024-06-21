package com.example.test.repository;

import com.example.test.domain.dto.MemberTeamDto;
import com.example.test.domain.dto.SearchCondition;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface MemberRepository {
    PageImpl<MemberTeamDto> findByWhere(SearchCondition condition, Pageable pageable);
}
