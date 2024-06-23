package com.example.test.domain;

import com.example.test.domain.dto.MemberTeamDto;
import com.example.test.domain.dto.QMemberTeamDto;
import com.example.test.domain.dto.SearchCondition;
import com.example.test.repository.MemberRepositoryDataJPA;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Random;

import static com.example.test.domain.QMember.member;
import static com.example.test.domain.QTeam.team;


@Slf4j
@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory factory;
    @Autowired
    private MemberRepositoryDataJPA memberRepositoryDataJPA;

    @BeforeEach
    void before() {
        factory = new JPAQueryFactory(em);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        for (int i = 0; i < 500; i++) {
            Team teamCheck = i % 2 == 0 ? teamA : teamB;
            em.persist(
                    new Member("member" + i, new Random().nextInt(50), new Address("Seoul", "SaDang-" + i), teamCheck));
        }
    }

    @Test
    void testV1() {
        SearchCondition condition = new SearchCondition();
        condition.setMemberName("member");
        condition.setTeamName("team");
        condition.setAgeGoe(0);
        condition.setAgeLoe(50);

        QMemberTeamDto qResult = new QMemberTeamDto(member.id, member.memberName, member.age, member.address.street,
                                                    member.address.city, team.teamName);
        List<MemberTeamDto> result = factory.select(qResult)
                .from(member)
                .join(member.team, team)
                .where(memberNameCheck(condition.getMemberName()), teamNameCheck(condition.getTeamName()),
                       ageGoeCheck(condition.getAgeGoe()), ageLoeCheck(condition.getAgeLoe()))
                .fetch();

        for (MemberTeamDto memberTeamDto : result) {
            System.out.println("memberTeamDto = " + memberTeamDto);
        }

    }

    private BooleanExpression memberNameCheck(String memberName) {
        return StringUtils.hasText(memberName) ? member.memberName.like("%" + memberName + "%") : null;
    }

    private BooleanExpression teamNameCheck(String teamName) {
        return StringUtils.hasText(teamName) ? team.teamName.like("%" + teamName + "%") : null;
    }

    private BooleanExpression ageGoeCheck(Integer ageGoe) {
        return ageGoe != null ? member.age.goe(ageGoe) : null;
    }

    private BooleanExpression ageLoeCheck(Integer ageLoe) {
        return ageLoe != null ? member.age.loe(ageLoe) : null;
    }

}