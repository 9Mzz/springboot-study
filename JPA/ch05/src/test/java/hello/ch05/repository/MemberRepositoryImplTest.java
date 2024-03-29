package hello.ch05.repository;

import ch.qos.logback.classic.Logger;
import hello.ch05.Ch05Application;
import hello.ch05.domain.Member;
import hello.ch05.domain.Team;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
@ContextConfiguration(classes = Ch05Application.class)
class MemberRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository;

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

    }
}