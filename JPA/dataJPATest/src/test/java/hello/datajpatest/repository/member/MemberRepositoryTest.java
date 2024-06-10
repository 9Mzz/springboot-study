package hello.datajpatest.repository.member;

import hello.datajpatest.domain.Member;
import hello.datajpatest.domain.Team;
import hello.datajpatest.domain.dto.MemberDto;
import hello.datajpatest.repository.team.TeamRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.swing.text.html.parser.Entity;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Slf4j
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository   teamRepository;

    @Autowired
    EntityManager em;

    @AfterEach
    void afterAct() {
        memberRepository.deleteAllInBatch();
        teamRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("메소드 이름으로 쿼리 생성")
    void findByMemberNameAndAgeGreaterThanEqual() {
        Team teamA = new Team("teamA");
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberB", 20, teamA));
        memberRepository.save(new Member("memberC", 20, teamA));
        memberRepository.save(new Member("memberD", 20, teamA));
        memberRepository.save(new Member("memberE", 20, teamA));

        Member memberA = memberRepository.findByMemberNameAndAgeGreaterThanEqual("memberA", 20);
        log.info("memberA: {}", memberA);
    }

    @Test
    @DisplayName("@Query, 리포지토리 메소드에 쿼리 정의하기")
    void findUser() {
        Team teamA = new Team("teamA");
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberB", 20, teamA));
        memberRepository.save(new Member("memberC", 20, teamA));
        memberRepository.save(new Member("memberD", 20, teamA));
        memberRepository.save(new Member("memberE", 20, teamA));

        List<Member> memberA = memberRepository.findUser("memberA", 20);
        for (Member member : memberA) {
            log.info("member: {}", member);
        }

    }

    @Test
    @DisplayName("@Query, 값, DTO 조회하기")
    void findMemberDto() {
        Team teamA = new Team("teamA");
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberB", 20, teamA));
        memberRepository.save(new Member("memberC", 20, teamA));
        memberRepository.save(new Member("memberD", 20, teamA));
        memberRepository.save(new Member("memberE", 20, teamA));

        List<MemberDto> dtoList = memberRepository.findMemberDto();
        for (MemberDto memberDto : dtoList) {
            log.info("memberDto = {}", memberDto);
        }

    }

    @Test
    void findCollection() {
        Team teamA = new Team("teamA");
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberB", 20, teamA));
        memberRepository.save(new Member("memberC", 20, teamA));
        memberRepository.save(new Member("memberD", 20, teamA));
        memberRepository.save(new Member("memberE", 20, teamA));

        List<Member> collection = memberRepository.findCollection(Arrays.asList("memberA", "memberB", "memberC"));
        for (Member member : collection) {
            log.info("member = {}", member);
        }

    }

    @Test
    void findByAge() {
        Team teamA = new Team("teamA");
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberB", 20, teamA));
        memberRepository.save(new Member("memberC", 20, teamA));
        memberRepository.save(new Member("memberD", 20, teamA));
        memberRepository.save(new Member("memberE", 20, teamA));

        int age    = 20;
        int offset = 0;
        int limit  = 3;

        PageRequest  pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "memberName"));
        Page<Member> memberPage  = memberRepository.findByAge(age, pageRequest);
        Page<MemberDto> memberDtos = memberPage.map(member -> new MemberDto(member.getId(), member.getMemberName(), member.getAge(), member.getTeam()
                .getTeamName()));
        List<MemberDto> content = memberDtos.getContent();
        for (MemberDto memberDto : content) {
            log.info("member = {}", memberDto);
        }

    }

    @Test
    void bulkAgePlus() {
        Team teamA = new Team("teamA");
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberB", 20, teamA));
        memberRepository.save(new Member("memberC", 20, teamA));
        memberRepository.save(new Member("memberD", 20, teamA));
        memberRepository.save(new Member("memberE", 20, teamA));

        int i = memberRepository.bulkAgePlus(20);
        log.info("i = {}", i);


    }

    @Test
    void entityGraph() {
        Team teamA = new Team("teamA");
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberB", 20, teamA));
        memberRepository.save(new Member("memberC", 20, teamA));
        memberRepository.save(new Member("memberD", 20, teamA));
        memberRepository.save(new Member("memberE", 20, teamA));

        em.flush();
        em.clear();

        List<Member> member = memberRepository.entityGraph();
        for (Member member1 : member) {
            log.info("member1 = {}", member1);
        }

    }

    @Test
    void queryHint() {
        Team teamA = new Team("teamA");
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberB", 20, teamA));
        memberRepository.save(new Member("memberC", 20, teamA));
        memberRepository.save(new Member("memberD", 20, teamA));
        memberRepository.save(new Member("memberE", 20, teamA));
        em.flush();
        em.clear();
        Member findMember = memberRepository.queryHint("memberA");
        findMember.setMemberName("test!!!!!!!!!!!!!!!");
        em.flush();
    }

    @Test
    void findByMemberName() {
        Team teamA = new Team("teamA");
        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberB", 20, teamA));
        memberRepository.save(new Member("memberC", 20, teamA));
        memberRepository.save(new Member("memberD", 20, teamA));
        memberRepository.save(new Member("memberE", 20, teamA));
        em.flush();
        em.clear();

        Member memberA = memberRepository.findByMemberName("memberA");
        log.info("memberA = {}", memberA);

    }


}