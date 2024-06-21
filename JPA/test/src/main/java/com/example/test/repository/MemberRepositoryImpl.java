package com.example.test.repository;

import com.example.test.domain.dto.MemberTeamDto;
import com.example.test.domain.dto.QMemberTeamDto;
import com.example.test.domain.dto.SearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;


import java.util.List;

import static com.example.test.domain.QMember.member;
import static com.example.test.domain.QTeam.team;


@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final EntityManager           em;
    private final JPAQueryFactory         factory;
    private final MemberRepositoryDataJPA memberRepositoryDataJPA;

    public MemberRepositoryImpl(EntityManager em, MemberRepositoryDataJPA memberRepositoryDataJPA) {
        this.em                      = em;
        this.factory                 = new JPAQueryFactory(em);
        this.memberRepositoryDataJPA = memberRepositoryDataJPA;
    }


    public PageImpl<MemberTeamDto> findByWhere(SearchCondition condition, Pageable pageable) {
        QMemberTeamDto qParam = new QMemberTeamDto(member.id, member.memberName, member.age, member.address.street,
                                                   member.address.city, team.teamName);

        List<MemberTeamDto> result = factory.select(qParam)
                .from(member)
                .leftJoin(member.team, team)
                .where(mNameCheck(condition.getMemberName()), tNameCheck(condition.getTeamName()),
                       mAgeGoeCheck(condition.getAgeGoe()), mAgeLoeCheck(condition.getAgeLoe()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = factory.select(member.count())
                .from(member)
                .where(mNameCheck(condition.getMemberName()), tNameCheck(condition.getTeamName()),
                       mAgeGoeCheck(condition.getAgeGoe()), mAgeLoeCheck(condition.getAgeLoe()))
                .leftJoin(member.team, team)
                .fetchOne();
        return new PageImpl<>(result, pageable, count);
    }

    private BooleanExpression mNameCheck(String memberName) {
        return StringUtils.hasText(memberName) ? member.memberName.like("%" + memberName + "%") : null;
    }

    private BooleanExpression tNameCheck(String teamName) {
        return StringUtils.hasText(teamName) ? team.teamName.like("%" + teamName + "%") : null;
    }


    private BooleanExpression mAgeGoeCheck(Integer ageGoe) {
        return ageGoe != null ? member.age.goe(ageGoe) : null;
    }

    private BooleanExpression mAgeLoeCheck(Integer ageLoe) {
        return ageLoe != null ? member.age.loe(ageLoe) : null;
    }

}
