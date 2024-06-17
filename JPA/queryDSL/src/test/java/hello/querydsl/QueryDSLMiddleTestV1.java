package hello.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.querydsl.domain.Member;
import hello.querydsl.domain.Team;
import hello.querydsl.domain.dto.MemberDto;
import hello.querydsl.domain.dto.QMemberDto;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static hello.querydsl.domain.QMember.member;

@SpringBootTest
@Transactional
@Slf4j
public class QueryDSLMiddleTestV1 {

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


    // 1 프로젝션과 결과 반환 - 프로젝션 대상이 하나
    @Test
    void simpleProjection() {
        List<String> result = query.select(member.userName)
                .from(member)
                .fetch();
        for (String s : result) {
            log.info("s = {}", s);
        }
    }

    // 프로젝션과 결과 반환 - 튜플 조회
    @Test
    void tupleProjection() {
        List<Tuple> result = query.select(member.userName, member.age)
                .from(member)
                .fetch();
        for (Tuple tuple : result) {
            String  userName = tuple.get(member.userName);
            Integer age      = tuple.get(member.age);

            log.info("userName = {}", userName);
            log.info("age = {}", age);
        }
    }

    // 2 프로젝션과 결과 반환 - 순수 JPA에서 DTO 조회
    @Test
    void findDtoByJPQL() {
        List<MemberDto> result = em.createQuery("select new hello.querydsl.domain.dto.MemberDto(m.userName, m.age) from Member m join m.team t ", MemberDto.class)
                .getResultList();
        for (MemberDto memberDto : result) {
            log.info("memberDto = {}", memberDto);
        }
    }

    // 2-2 프로젝션과 결과 반환 - Querydsl 빈 생성(Bean population)

    // 프로퍼티 접근 Setter - DTO 에 기본 생성자와 Setter 가 필요
    @Test
    void findDtoBySetter() {
        List<MemberDto> result = query.select(Projections.bean(MemberDto.class, member.userName, member.age))
                .from(member)
                .fetch();
        for (MemberDto memberDto : result) {
            log.info("memberDto = {}", memberDto);
        }
    }

    // 필드 직접 접근 - DTO 에 기본 생성자가 필요
    @Test
    void findDtoByField() {
        List<MemberDto> result = query.select(Projections.fields(MemberDto.class, member.userName, member.age))
                .from(member)
                .fetch();
        for (MemberDto memberDto : result) {
            log.info("memberDto = {}", memberDto);
        }
    }

    // 생성자 사용
    @Test
    void findDtoByConstructor() {
        List<MemberDto> result = query.select(Projections.constructor(MemberDto.class, member.userName, member.age))
                .from(member)
                .fetch();
        for (MemberDto memberDto : result) {
            log.info("memberDto = {}", memberDto);
        }
    }

    // @QueryProjection 활용
    @Test
    void findDtoByQueryProjection() {
        List<MemberDto> result = query.select(new QMemberDto(member.userName, member.age))
                .from(member)
                .fetch();

        for (MemberDto memberDto : result) {
            log.info("memberDto = {}", memberDto);
        }
    }

    // 3 distinct 사용
    @Test
    void useDistinct() {
        List<Integer> result = query.select(member.age)
                .distinct()
                .from(member)
                .fetch();
        for (Integer i : result) {
            log.info("i = {}", i);
        }
    }

}
