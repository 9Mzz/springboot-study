package com.example.test.repository;

import com.example.test.domain.Member;
import com.example.test.domain.QMember;
import com.example.test.domain.dto.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.test.domain.QMember.member;
import static com.example.test.domain.QTeam.team;

@Repository
@Transactional
public class MemberRepository {

    private final EntityManager          em;
    private final JPAQueryFactory        factory;
    private final MemberRepositoryCustom memberRepositoryCustom;


    public MemberRepository(EntityManager em, MemberRepositoryCustom memberRepositoryCustom) {
        this.em                     = em;
        this.factory                = new JPAQueryFactory(em);
        this.memberRepositoryCustom = memberRepositoryCustom;
    }

    public int bulkUpdate(int ageParam) {
        return memberRepositoryCustom.bulkAgePlus(ageParam);
    }


    public List<MemberCaseDto> caseBuilder(int ageParam) {

        StringExpression otherwise = new CaseBuilder().when(member.age.between(0, 20))
                .then("미성년자")
                .when(member.age.between(21, 40))
                .then("MZ 세대")
                .otherwise("기타");

        return factory.select(new QMemberCaseDto(member.memberName, member.age, otherwise))
                .from(member)
                .where(member.age.goe(ageParam))
                .fetch();
    }

    public List<Member> jpaExpressionPractice() {
        QMember subMember = new QMember("subMember");
        JPQLQuery<Double> subQuery = JPAExpressions.select(subMember.age.avg())
                .from(subMember)
                .where(subMember.age.between(0, 40));

        return factory.select(member)
                .from(member)
                .where(member.age.goe(subQuery))
                .fetch();

    }

    public Page<MemberTeamDto> memberPagingTest(SearchCondition condition, Pageable pageable) {

        List<MemberTeamDto> content = factory.select(
                        new QMemberTeamDto(member.id, member.memberName, member.age, member.address.street, member.address.city,
                                           team.teamName))
                .from(member)
                .leftJoin(member.team, team)
                .where(memberNameCheck(condition.getMemberNameParam()), teamNameCheck(condition.getTeamNameParam()),
                       ageGoeCheck(condition.getAgeGoe()), ageLoeCheck(condition.getAgeLoe()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long total = factory.select(member.count())
                .from(member)
                .leftJoin(member.team, team)
                .where(memberNameCheck(condition.getMemberNameParam()), teamNameCheck(condition.getTeamNameParam()),
                       ageGoeCheck(condition.getAgeGoe()), ageLoeCheck(condition.getAgeLoe()))
                .fetchOne();
        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression memberNameCheck(String memberNameParam) {
        return StringUtils.hasText(memberNameParam) ? member.memberName.like("%" + memberNameParam + "%") : null;
    }


    private BooleanExpression teamNameCheck(String teamNameParam) {
        return StringUtils.hasText(teamNameParam) ? team.teamName.like("%" + teamNameParam + "%") : null;
    }

    private BooleanExpression ageGoeCheck(Integer ageGoe) {
        return ageGoe != null ? member.age.goe(ageGoe) : null;
    }

    private BooleanExpression ageLoeCheck(Integer ageLoe) {
        return ageLoe != null ? member.age.loe(ageLoe) : null;
    }
}
