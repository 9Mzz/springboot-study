package hello.querydsl.repository;

import hello.querydsl.domain.condition.MemberSearchCondition;
import hello.querydsl.domain.dto.MemberTeamDto;
import hello.querydsl.web.MemberController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.sound.midi.MetaMessage;
import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberTeamDto> searchByBuilder(MemberSearchCondition condition);

    List<MemberTeamDto> searchByWhere(MemberSearchCondition condition);

    Page<MemberTeamDto> searchPage(MemberSearchCondition condition, Pageable pageable);

    Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable);
}