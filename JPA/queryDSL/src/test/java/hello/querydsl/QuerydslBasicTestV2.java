package hello.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
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

import static com.querydsl.jpa.JPAExpressions.select;
import static hello.querydsl.domain.QMember.member;

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
        Member memberB = new Member("memberB", 20, teamA);
        Member memberC = new Member("memberC", 30, teamA);
        Member memberD = new Member("memberD", 40, teamB);
        em.persist(memberA);
        em.persist(memberB);
        em.persist(memberC);
        em.persist(memberD);
    }

    /**
     * 조건부에 JPAExpressions 사용
     */
    @Test
    @DisplayName("11 서브 쿼리")
    void subQuery() {
        QMember memberSub = new QMember("member");
        List<Member> fetch = query.select(member)
                .from(member)
                .where(member.age.eq(select(memberSub.age.max()).from(memberSub)))
                .fetch();
        for (Member fetch1 : fetch) {
            log.info("fetch1: {}", fetch1);
        }

        QMember subMember = new QMember("subMember");
        query.select(member)
                .from(member)
                .where(member.age.eq(select(subMember.age.max()).from(subMember)))
                .fetch();
    }

    @Test
    @DisplayName("11 서브 쿼리 goe 사용")
    void subQueryGoe() {
        QMember subMember = new QMember("subMember");
        query.select(member)
                .from(member)
                .where(member.age.goe(select(subMember.age.avg()).from(subMember)))
                .fetch();
    }

    @Test
    @DisplayName("11 서브쿼리 여러 건 처리 in 사용")
    void subQueryIn() {
        QMember subMember = new QMember("subMember");
        List<Member> result = query.selectFrom(member)
                .where(member.age.in(select(subMember.age).from(subMember)
                        .where(subMember.age.gt(10))))
                .fetch();
        for (Member member1 : result) {
            log.info("member1: {}", member1);
        }
    }

    @Test
    @DisplayName("11 서브쿼리 select 절에 subquery")
    void selectSubQuery() {
        QMember subMember = new QMember("subMember");
        List<Tuple> result = query.select(member.userName, select(subMember.age.avg()).from(subMember))
                .from(member)
                .fetch();
        for (Tuple tuple : result) {
            log.info("tuple: {}", tuple);
            log.info("tuple userName : {}", tuple.get(member.userName));
            log.info("tuple subMember : {}", tuple.get(select(subMember.age.avg()).from(subMember)));
        }
    }

    // 12-1 Case 문
    @Test
    void basicCase() {
        List<Tuple> result = query.select(member.userName, member.age.when(10)
                        .then("열살")
                        .when(20)
                        .then("스무살")
                        .otherwise("기타"))
                .from(member)
                .fetch();
        for (Tuple tuple : result) {
            log.info("tuple: {}", tuple);
        }
    }

    // 12-2 복잡한 조건
    @Test
    void complexCase() {
        List<String> result = query.select(new CaseBuilder().when(member.age.between(1, 20))
                        .then("미성년자")
                        .when(member.age.between(21, 30))
                        .then("21 ~ 30살")
                        .otherwise("기타"))
                .from(member)
                .fetch();

        for (String s : result) {
            log.info("s = {}", s);
        }
    }

    // 12-3 orderBy 에서 Case 문 함께 사용하기 예제
    @Test
    void exampleCase() {
        NumberExpression<Integer> rankPath = new CaseBuilder().when(member.age.between(0, 20))
                .then(1)
                .when(member.age.between(21, 30))
                .then(2)
                .otherwise(3);

        List<Tuple> result = query.select(member.userName, member.age, rankPath)
                .from(member)
                .orderBy(rankPath.desc())
                .fetch();
        for (Tuple tuple : result) {
            String userName = tuple.get(member.userName);
            Integer age = tuple.get(member.age
            );
            Integer rank = tuple.get(rankPath);
            log.info("userName = {}, age = {}, rank = {}", userName, age, rank);
        }
    }

    // 13. 상수, 문자 더하기
    @Test
    void constant() {
        List<Tuple> fetch = query.select(member.userName, Expressions.constant("A"))
                .from(member)
                .fetch();
        for (Tuple tuple : fetch) {
            log.info("tuple: {}", tuple);
        }
    }

    // 문자 더하기 concat
    @Test
    public void concat() {
        List<String> fetch = query.select(member.userName.concat("_")
                        .concat(member.age.stringValue()))
                .from(member)
                .where(member.userName.eq("memberA"))
                .fetch();
        for (String s : fetch) {
            log.info("s = {}", s);
        }

    }


}


