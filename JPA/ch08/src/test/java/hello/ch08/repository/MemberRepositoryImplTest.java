package hello.ch08.repository;

import ch.qos.logback.classic.Logger;
import hello.ch08.domain.Member;
import hello.ch08.domain.Team;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MemberRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        Team teamA = new Team();
        teamA.setTeamName("teamA");

        Member memberA = new Member();
        memberA.setMemberName("memberA");
        memberA.setTeam(teamA);

        Member saveMember = memberRepository.save(memberA);
        Member findMember = memberRepository.findById(saveMember.getId());
        log.info("saveMember = {}", saveMember);
        log.info("findMember= {}", findMember);

    }
}