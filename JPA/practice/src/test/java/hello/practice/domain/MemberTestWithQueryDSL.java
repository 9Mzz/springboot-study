package hello.practice.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.EntityManagerFactoryAccessor;

import java.util.List;

import static hello.practice.domain.QMember.member;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Slf4j
class MemberTestWithQueryDSL {

    @Autowired
    EntityManager em;

    @BeforeEach
    void before() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        Member memberA = new Member("memberA", 42);
        memberA.setTeam(teamA);
        teamA.getMembers()
                .add(memberA);
        Member memberB = new Member("memberB", 11);
        memberB.setTeam(teamA);
        teamA.getMembers()
                .add(memberB);
        Member memberC = new Member("memberC", 43);
        memberC.setTeam(teamA);
        teamA.getMembers()
                .add(memberC);
        Member memberD = new Member("memberD", 25);
        memberD.setTeam(teamA);
        teamA.getMembers()
                .add(memberD);
        Member memberE = new Member("memberE", 11);
        memberE.setTeam(teamA);
        teamA.getMembers()
                .add(memberE);

        em.persist(teamA);
    }

    @AfterEach
    void after() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        query.delete(member)
                .execute();
        em.clear();
    }

    @Test
    void queryDSLBasicTest() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        List<Member> result = query.selectFrom(member)
                .orderBy(member.name.desc())
                .fetch();
        for (Member memberResult : result) {
            log.info("member = {}", memberResult);
        }
    }

    @Test
    void pagingTest() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        List<Member> pagingResult
                = query.selectFrom(member)
                .offset(2)
                .limit(5)
                .fetch();
        for (Member members : pagingResult) {
            log.info("members = {}", members);
        }

    }
}