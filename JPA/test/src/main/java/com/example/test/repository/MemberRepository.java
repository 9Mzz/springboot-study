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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Random;

import static com.example.test.domain.QMember.*;
import static com.example.test.domain.QTeam.*;

@Transactional
@Repository
public class MemberRepository {

    private final EntityManager          em;
    private final JPAQueryFactory        factory;
    private final MemberRepositoryCustom memberRepositoryCustom;

    public MemberRepository(EntityManager em, MemberRepositoryCustom memberRepositoryCustom) {
        this.em                     = em;
        this.factory                = new JPAQueryFactory(em);
        this.memberRepositoryCustom = memberRepositoryCustom;
    }

    public List<testV1Dto> testV1() {
        return factory.select(
                        new QtestV1Dto(member.id, member.name, member.age, member.address.city, member.address.street,
                                       team.teamName))
                .from(member)
                .orderBy(member.age.desc())
                .fetch();
    }

    public List<Member> testV2() {
        return factory.select(member)
                .from(member)
                .leftJoin(member.team, team)
                .fetchJoin()
                .fetch();
    }

    public List<Member> testV3() {
        QMember subMember = new QMember("subMember");
        JPQLQuery<Integer> where = JPAExpressions.select(subMember.age)
                .from(subMember)
                .where(subMember.age.goe(new Random().nextInt(20)));

        return factory.select(member)
                .from(member)
                .where(member.age.in(where))
                .orderBy(member.age.desc())
                .fetch();
    }

    public List<testV4Dto> testV4() {
        CaseBuilder builder = new CaseBuilder();
        StringExpression otherwise = builder.when(member.age.between(1, 20))
                .then("미성년자")
                .when(member.age.between(21, 40))
                .then("mz 세대")
                .otherwise("성인");
        return factory.select(new QtestV4Dto(member.name, member.age, otherwise))
                .from(member)
                .orderBy(member.age.desc())
                .fetch();
    }

    public PageImpl<testV1Dto> testV5(SearchCondition condition, Pageable pageable) {
        List<testV1Dto> content = factory.select(
                        new QtestV1Dto(member.id, member.name, member.age, member.address.city, member.address.street,
                                       team.teamName))
                .from(member)
                .leftJoin(member.team, team)
                .where(memberNameCheck(condition.getMemberNameParam()), teamNameCheck(condition.getTeamNameParam()),
                       memberAgeGoeCheck(condition.getAgeGoe()), memberAgeLoeCheck(condition.getAgeLoe()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long result = factory.select(member.count())
                .from(member)
                .leftJoin(member.team, team)
                .where(memberNameCheck(condition.getMemberNameParam()), teamNameCheck(condition.getTeamNameParam()),
                       memberAgeGoeCheck(condition.getAgeGoe()), memberAgeLoeCheck(condition.getAgeLoe()))
                .fetchOne();

        return new PageImpl<>(content, pageable, result);

    }

    private BooleanExpression memberNameCheck(String memberNameParam) {
        return StringUtils.hasText(memberNameParam) ? member.name.like("%" + memberNameParam + "%") : null;
    }

    private BooleanExpression teamNameCheck(String teamNameParam) {
        return StringUtils.hasText(teamNameParam) ? team.teamName.like("%" + teamNameParam + "%") : null;
    }

    private BooleanExpression memberAgeGoeCheck(Integer ageGoe) {
        return ageGoe != null ? member.age.goe(ageGoe) : null;
    }

    private BooleanExpression memberAgeLoeCheck(Integer ageLoe) {
        return ageLoe != null ? member.age.loe(ageLoe) : null;
    }

}
