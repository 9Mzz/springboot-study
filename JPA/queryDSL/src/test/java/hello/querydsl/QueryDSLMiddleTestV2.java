package hello.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
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
import org.springframework.test.annotation.Commit;

import java.util.List;

import static hello.querydsl.domain.QMember.member;

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

    // 4 동적 쿼리 - BooleanBuilder 사용
    @Test
    void dynamicQuery_BooleanBuilder() {
        String  userNameParam = "memberA";
        Integer ageParam      = 10;

        List<Member> searchMemberV1 = searchMemberV1(userNameParam, ageParam);
        for (Member member1 : searchMemberV1) {
            log.info("member = {}", member1);
        }

    }

    private List<Member> searchMemberV1(String userNameParam, Integer ageParam) {
        BooleanBuilder builder = new BooleanBuilder();
        if (userNameParam != null) {
            builder.and(member.userName.eq(userNameParam));
        }
        if (ageParam != null) {
            builder.and(member.age.eq(ageParam));
        }
        return query.select(member)
                .from(member)
                .where(builder)
                .fetch();
    }

    // 5 동적 쿼리 - Where 다중 파라미터 사용
    @Test
    void dynamicQuery_WhereParam() {
        String  userNameParam = "membeerA";
        Integer ageParam      = 10;

        List<Member> searchMemberV2 = searchMemberV2(userNameParam, ageParam);
        for (Member member : searchMemberV2) {
            log.info("member = {}", member);
        }

    }

    private List<Member> searchMemberV2(String userNameCond, Integer ageCond) {
        return query.select(member)
                .from(member)
                .where(userNameCond(userNameCond), ageCond(ageCond))
                .fetch();
    }

    private BooleanExpression userNameCond(String userNameCond) {
        return userNameCond != null ? member.userName.eq(userNameCond) : null;
    }

    private BooleanExpression ageCond(Integer ageCond) {
        return ageCond != null ? member.age.eq(ageCond) : null;
    }

    // 6 수정, 삭제 벌크 연산
    @Test
    @Commit
    void buliUpdate() {
        long userNameCount = query.update(member)
                .set(member.userName, "비회원")
                .where(member.age.goe(5))
                .execute();
        long ageAddCount = query.update(member)
                .set(member.age, member.age.add(1))
                .execute();
        long ageDeleteCount = query.update(member)
                .set(member.age, member.age.add(-1))
                .execute();
        long ageDoubleCount = query.update(member)
                .set(member.age, member.age.multiply(2))
                .execute();
        em.flush();
        em.clear();

        log.info("userNameCount = {}", userNameCount);
        log.info("ageAddCount = {}", ageAddCount);
        log.info("ageDeleteCount = {}", ageDeleteCount);
        log.info("ageDoubleCount = {}", ageDoubleCount);

        List<Member> result = query.selectFrom(member)
                .fetch();
        for (Member member : result) {
            log.info("member = {}", member);
        }
    }

    // 쿼리 한번으로 대량 데이터 삭제
    @Test
    void bulkDelete() {
        long deleteCount = query.delete(member)
                .where(member.age.loe(18))
                .execute();
        log.info("deleteCount = {}", deleteCount);
    }

    // 7 SQL function 호출하기
    @Test
    void sqlFunction() {
        List<String> result = query.select(Expressions.stringTemplate("function('replace', {0}, {1}, {2})", member.userName, "member", "M"))
                .from(member)
                .fetch();
        for (String s : result) {
            log.info("s = {}", s);
        }
    }

    @Test
    void testQuery() {
        long count = query.update(member)
                .set(member.age, member.age.add(5))
                .execute();
        em.flush();
        em.clear();
        log.info("count = {}", count);
        List<Member> result = query.select(member)
                .from(member)
                .fetch();
        for (Member member1 : result) {
            System.out.println("member1 = " + member1);
        }


    }

}
