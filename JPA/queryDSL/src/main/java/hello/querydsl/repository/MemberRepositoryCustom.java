package hello.querydsl.repository;

import hello.querydsl.domain.condition.MemberSearchCondition;
import hello.querydsl.domain.dto.MemberTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberTeamDto> searchByBuilder(MemberSearchCondition condition);

    List<MemberTeamDto> searchByWhere(MemberSearchCondition condition);

    Page<MemberTeamDto> searchPage(MemberSearchCondition condition, Pageable pageable);
}