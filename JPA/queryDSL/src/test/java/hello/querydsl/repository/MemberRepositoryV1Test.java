package hello.querydsl.repository;

import hello.querydsl.domain.Member;
import hello.querydsl.domain.Team;
import hello.querydsl.domain.condition.MemberSearchCondition;
import hello.querydsl.domain.dto.MemberTeamDto;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class MemberRepositoryV1Test {

    @Autowired
    EntityManager      em;
    @Autowired
    MemberRepositoryV1 memberRepositoryV1;

    @Test
    void basicTest() {
        Member memberA = new Member("memberA", 20);
        memberRepositoryV1.save(memberA);

        Member findMember = memberRepositoryV1.findById(memberA.getId())
                .get();
        assertThat(findMember).isEqualTo(memberA);

        List<Member> members = memberRepositoryV1.findAll();
        assertThat(members).containsExactly(memberA);

        List<Member> findMemberName = memberRepositoryV1.findByName(memberA.getUserName());
        assertThat(findMemberName).containsExactly(memberA);
    }

    @Test
    void basicQueryDSLTest() {
        Member memberA = new Member("memberA", 20);
        memberRepositoryV1.save(memberA);

        Member findMember = memberRepositoryV1.findById(memberA.getId())
                .get();
        assertThat(findMember).isEqualTo(memberA);

        List<Member> members = memberRepositoryV1.findAll_QueryDSL();
        assertThat(members).containsExactly(memberA);

        List<Member> findMemberName = memberRepositoryV1.findByName_QueryDSL(memberA.getUserName());
        assertThat(findMemberName).containsExactly(memberA);
    }

    @Test
    void searchTest() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member memberA = new Member("memberA", 20, teamA);
        Member memberB = new Member("memberB", 20, teamA);
        Member memberC = new Member("memberC", 25, teamB);
        em.persist(memberA);
        em.persist(memberB);
        em.persist(memberC);

        String  userNameParam = "member";
        String  teamNameParam = "team";
        Integer goeParam      = 0;
        Integer loeParam      = 40;

        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setUserName(userNameParam);
        condition.setTeamName(teamNameParam);
        condition.setAgeGoe(goeParam);
        condition.setAgeLoe(loeParam);

        // List<MemberTeamDto> result = memberRepository.searchByBuilder(condition);
        List<MemberTeamDto> result = memberRepositoryV1.searchByWhere(condition);
        for (MemberTeamDto memberTeamDto : result) {
            log.info("memberTeamDto: {}", memberTeamDto);
        }
    }

}