package hello.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.querydsl.domain.Member;
import hello.querydsl.domain.QTeam;
import hello.querydsl.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.List;

import static hello.querydsl.domain.QMember.member;
import static hello.querydsl.domain.QTeam.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @Autowired
    EntityManager em;
    JPAQueryFactory factory;
    String          param = "memberA";
    @Autowired
    private DispatcherServlet dispatcherServlet;


    @BeforeEach
    void setUp() {
        // EntityManager 자체가 동시성, 즉 멀티스레드에 아무 문제 없게 설계되어 있기 때문에 개발자가 신경 쓸 필요가 없다.
        factory = new JPAQueryFactory(em);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);
        Member memberA = new Member("memberA", 10, teamA);
        Member memberB = new Member("memberB", 10, teamA);
        Member memberC = new Member("memberC", 10, teamA);
        Member memberD = new Member("memberD", 10, teamB);
        em.persist(memberA);
        em.persist(memberB);
        em.persist(memberC);
        em.persist(memberD);
    }

    /**
     * Querydsl vs JPQL
     */
    @Test
    @DisplayName("Querydsl vs JPQL : JPQL")
    void startJPQL() {

        // memberA 찾기
        Member result = em.createQuery("select m from Member m where m.userName = :userName", Member.class)
                .setParameter("userName", param)
                .getSingleResult();
        log.info("startJPQL result: {}", result);
    }

    @Test
    @DisplayName("Querydsl vs JPQL : Querydsl")
    void startQuerydsl() {
        // memberA 찾기
        Member result = factory.selectFrom(member)
                .where(member.userName.eq(param))
                .fetchOne();
        log.info("startQuerydsl result: {}", result);
    }

    @Test
    @DisplayName("startQuerydsl3")
    void startQuerydsl3() {
        Member findMember = factory.selectFrom(member)
                .where(member.userName.eq(param))
                .fetchOne();
        assertThat(findMember.getUserName()).isEqualTo("memberA");
    }

    @Test
    @DisplayName("검색 조건 쿼리")
    void search() {
        Member memberA = factory.selectFrom(member)
                .where(member.userName.eq(param), member.age.between(10, 20))
                .fetchOne();
        log.info("search result: {}", memberA);
    }

    @Test
    @DisplayName("JPQL이 제공하는 모든 검색 조건 제공")
    void search2() {
        member.userName.eq("member1");  // username = 'member1'
        member.userName.ne("member1");  // username != 'member1'
        member.userName.eq("member1")
                .not(); // username != 'member1'

        member.userName.isNotNull();    // 이름이 is not null

        member.age.in(10, 20);      // age in (10,20)
        member.age.notIn(10, 20);   // age not in (10, 20)
        member.age.between(10, 30); // between 10, 30

        member.age.goe(30); // age >= 30
        member.age.gt(30); // age > 30
        member.age.loe(30);   // age <= 30
        member.age.lt(30); // age < 30

        member.userName.like("member%"); // like 검색
        member.userName.contains("member"); // like ‘%member%’ 검색
        member.userName.startsWith("member"); // like ‘member%’ 검색 ...
    }

    @Test
    @DisplayName("4. 결과 조회")
    void result() {
        // List 조회, 데이터가 없으면 빈 List 반환
        List<Member> fetch = factory.selectFrom(member)
                .fetch();
        log.info("fetch = {}", fetch);

        // 단 건 조회
        // 결과가 없으면 : null
        // 결과가 둘 이상이면 : NonUniqueResultException 발생
        Member fetchOne = factory.selectFrom(member)
                .where(member.id.eq(1L))
                .fetchOne();
        log.info("fetchOne = {}", fetchOne);

        // limit(1).fetchOne()
        Member fetchFirst = factory.selectFrom(member)
                .fetchFirst();
        log.info("fetchFirst = {}", fetchFirst);

        // 페이징 정보 포함, total count 쿼리 추가 실행
        QueryResults<Member> fetchResults = factory.selectFrom(member)
                .fetchResults();
        log.info("fetchResults = {}", fetchResults);

        // count 쿼리로 쿼리 변경해서 count 수 조회
        long fetchCount = factory.selectFrom(member)
                .fetchCount();
        log.info("fetchCount = {}", fetchCount);
    }

    @Test
    @DisplayName("5. 정렬")
    void sort() {
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));

        List<Member> result = factory.selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.userName.asc()
                        .nullsLast())
                .fetch();
        Member member5    = result.get(0);
        Member member6    = result.get(1);
        Member memberNull = result.get(2);
        log.info("member5 = {}", member5);
        log.info("member6 = {}", member6);
        log.info("memberNull = {}", memberNull);
    }

    @Test
    @DisplayName("6. 페이징")
    void pagingV1() {
        List<Member> result = factory.selectFrom(member)
                .orderBy(member.id.desc()
                        .nullsFirst())
                .offset(0)
                .limit(3)
                .fetch();
        for (Member member1 : result) {
            log.info("member = {}", member1);
        }
    }

    @Test
    @DisplayName("6. 페이징 2")
    void pagingV2() {
        QueryResults<Member> queryResults = factory.selectFrom(member)
                .orderBy(member.id.desc())
                .offset(1)
                .limit(2)
                .fetchResults();
        log.info("queryResults.getTotal() = {}", queryResults.getTotal());
        log.info("queryResults.getLimit() = {}", queryResults.getLimit());
        log.info("queryResults.getOffset() = {}", queryResults.getOffset());
        log.info("queryResults.getResults() = {}", queryResults.getResults());
    }

    @Test
    @DisplayName("fetchResult 미지원 이후")
    void pagingv3() {
        int offset = 0;
        int limit  = 3;
        List<Member> content = factory.selectFrom(member)
                .leftJoin(member.team, team)
                .where(member.age.goe(5))
                .offset(offset)
                .limit(limit)
                .fetch();

        Long count = factory.select(member.count())
                .from(member)
                .leftJoin(member.team, team)
                .where(member.age.goe(5))
                .fetchOne();

        for (Member member1 : content) {
            log.info("member = {}", member1);
        }
        log.info("count = {}", count);


    }

}