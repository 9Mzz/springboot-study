package hello.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.querydsl.domain.Member;
import hello.querydsl.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
@Transactional
public class QueryDSLMiddleTestV2 {

    @Autowired
    EntityManager em;
    JPAQueryFactory query;

    @BeforeEach
    void setUp() {
        query = new JPAQueryFactory(em);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member memberA = new Member("memberA", 10, teamA);
        Member memberB = new Member("memberB", 10, teamA);
        Member memberC = new Member("memberC", 10, teamA);
        Member memberD = new Member("memberD", 10, teamB);
        Member memberE = new Member("memberE", 15, teamB);
        em.persist(memberA);
        em.persist(memberB);
        em.persist(memberC);
        em.persist(memberD);
        em.persist(memberE);
    }

    // 동적 쿼리 - BooleanBuilder 사용
    @Test
    void dynamicQuery_BooleanBuilder() {
        
    }

}
