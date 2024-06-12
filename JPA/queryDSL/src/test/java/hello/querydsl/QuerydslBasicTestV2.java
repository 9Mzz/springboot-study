package hello.querydsl;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.querydsl.domain.Member;
import hello.querydsl.domain.QMember;
import hello.querydsl.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static hello.querydsl.domain.QMember.*;

@SpringBootTest
@Transactional
@Slf4j
public class QuerydslBasicTestV2 {

    @Autowired
    EntityManager em;
    JPAQueryFactory query;

    @BeforeEach
    void before() {
        // EntityManager 자체가 동시성, 즉 멀티스레드에 아무 문제 없게 설계되어 있기 때문에 개발자가 신경 쓸 필요가 없다.
        query = new JPAQueryFactory(em);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member memberA = new Member("memberA", 10, teamA);
        Member memberB = new Member("memberB", 10, teamA);
        Member memberC = new Member("memberC", 15, teamA);
        Member memberD = new Member("memberD", 10, teamB);
        em.persist(memberA);
        em.persist(memberB);
        em.persist(memberC);
        em.persist(memberD);
    }

    @Test
    @DisplayName("11 서브 쿼리")
    void subQuery() {
        QMember memberSub = new QMember("member");

        List<Member> fetch = query.select(member)
                .from(member)
                .where(member.age.eq(JPAExpressions.select(memberSub.age.max())
                        .from(memberSub)))
                .fetch();
        for (Member fetch1 : fetch) {
            log.info("fetch1: {}", fetch1);
        }


    }
}


