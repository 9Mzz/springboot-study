package hello.ch05.repository;

import ch.qos.logback.classic.Logger;
import hello.ch05.Ch05Application;
import hello.ch05.domain.Member;
import hello.ch05.domain.Team;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.internal.creation.bytebuddy.MockMethodAdvice;
import org.mockito.invocation.InvocationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestAttributeMethodArgumentResolver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
@ContextConfiguration(classes = Ch05Application.class)
class MembersRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository   teamRepository;

    @Test
    void save() {
        Team teamA = new Team();
        teamA.setTeamName("teamA");

        Member memberA = new Member();
        memberA.setUserName("memberA");
        memberA.setTeam(teamA);

        Member savedMember = memberRepository.save(memberA);
        Member findMember  = memberRepository.findById(1L);
        log.info("findMember = {}", findMember);
        memberRepository.memberRemove(findMember.getId());
    }

    @Test
    void 순수한객체_단방향() {

        Team   teamA   = new Team("teamA");
        Member memberA = new Member("회원1");
        Member memberB = new Member("회원2");

        memberA.setTeam(teamA);
        memberA.setTeam(teamA);

        List<Member> members = teamA.getMembers();
        log.info("순수한객체_단방향 members.size = {}", members.size());
    }

    @Test
    void 순수한객체_양방향() {

        Team   teamA   = new Team("teamA");
        Member memberA = new Member("회원1");
        Member memberB = new Member("회원2");

        memberA.setTeam(teamA);
        teamA.getMembers().add(memberA);

        memberB.setTeam(teamA);
        teamA.getMembers().add(memberB);

        List<Member> members = teamA.getMembers();
        log.info("순수한객체_양방향 members.size = {}", members.size());
    }

    @Test
    void testORM_양방향() {
        // teamA 저장
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);

        Member memberA = new Member("회원A");
        memberA.setTeam(teamA);
        teamA.getMembers().add(memberA);
        memberRepository.save(memberA);

        Member memberB = new Member("회원B");
        memberB.setTeam(teamA);
        teamA.getMembers().add(memberB);
        memberRepository.save(memberB);
    }

    @Test
    void testORM_양방향_리팩토링() {
        // teamA 저장
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);

        Member memberA = new Member("회원A");
        memberA.setTeam(teamA);
        memberRepository.save(memberA);

        Member memberB = new Member("회원B");
        memberB.setTeam(teamA);
        memberRepository.save(memberB);
    }

}