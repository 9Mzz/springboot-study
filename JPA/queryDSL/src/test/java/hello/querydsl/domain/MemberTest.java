package hello.querydsl.domain;

import ch.qos.logback.classic.Logger;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.querydsl.QuerydslPracticeApplicationTests;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.UDecoder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.List;

import static hello.querydsl.domain.QMember.member;
import static hello.querydsl.domain.QTeam.*;

@Slf4j
@Transactional
@SpringBootTest
class MemberTest {

    @Autowired
    EntityManager em;

    @Test
    void memberTest() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        query.selectFrom(member);
    }

    /*
        @Test
        void 검색_조건() {
            JPAQueryFactory query = new JPAQueryFactory(em);
            query.select(member)
                    .from(member)
                    .where(
                            member.userName.eq("memberA")   //userName = memberA
                            member.userName.ne("memberA")   //uerName != memberA
                            member.userName.eq("memberA").not() //userName != memberA
                            member.userName.isNotNull() //userName is not Null
                            member.age.in(10, 20)   //age = 10 or 20
                            member.age.notIn(10, 20)    //age != 10 or 20
                            member.age.between(10, 20)  //  10 < age <20
                            member.age.gt(20)   //age > 20
                            member.age.goe(20)  // age >= 20
                            member.age.lt(20)   //age < 20
                            member.age.loe(20)  //age <= 20
                            member.userName.like("member") // userName = member%
                            member.userName.startsWith("member")    //userName = member%
                            member.userName.contains("member")  //userName = %member%
                    )
        }
     */
    @Test
    void and검색() {
        // and의 경우 연쇄체인이 아닌 , 로 비교하여 사용할 수 있다
        // 콤마로 구분하는 방식을 선호한다.
        JPAQueryFactory query = new JPAQueryFactory(em);
        query.selectFrom(member)
                .where(member.userName.eq("memberA")
                        .and(member.age.eq(10)))
                .fetchOne();
        query.selectFrom(member)
                .where(member.userName.eq("memberA"), member.age.eq(10))
                .fetchOne();

        // 단건 조회
        query.selectFrom(member)
                .where(member.age.eq(10))
                .fetchOne();
        // 리스트 조회
        query.selectFrom(member)
                .where(member.age.eq(10))
                .fetch();
        // 페이징
        query.selectFrom(member)
                .where(member.age.eq(10))
                .offset(1)
                .limit(5);
    }

    @Test
    void 정렬() {
        JPAQueryFactory query = new JPAQueryFactory(em);

        em.persist(new Member("memberA", 10));
        em.persist(new Member("memberB", 20));
        em.persist(new Member("memberC", 30));

        List<Tuple> result = query.select(member.id, member.userName, member.age)
                .from(member)
                .fetch();
        for (Tuple tuple : result) {
            log.info("tuple: {}", tuple);
        }
        Tuple tuple = result.get(0);
        log.info("tuple: {}", tuple);
    }

    @Test
    void 기본_조인() {
        JPAQueryFactory query   = new JPAQueryFactory(em);
        Team            teamA   = new Team("teamA");
        Member          memberA = new Member("memberA", 10);

        memberA.setTeam(teamA);
        teamA.getMembers()
                .add(memberA);
        em.persist(teamA);

        List<Member> teamResult = query.selectFrom(member)
                .join(member.team, team)
                .where(team.teamName.eq("teamA"))
                .fetch();

        for (Member memberResult : teamResult) {
            log.info("memberResult: {}", memberResult);
        }
    }


}