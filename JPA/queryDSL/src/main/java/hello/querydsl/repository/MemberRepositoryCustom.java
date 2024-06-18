package hello.querydsl.repository;

import hello.querydsl.domain.condition.MemberSearchCondition;
import hello.querydsl.domain.dto.MemberDto;
import hello.querydsl.domain.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberTeamDto> searchByBuilder(MemberSearchCondition condition);

    List<MemberTeamDto> searchByWhere(MemberSearchCondition condition);

}
