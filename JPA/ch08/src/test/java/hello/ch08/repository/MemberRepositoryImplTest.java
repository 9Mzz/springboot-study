package hello.ch08.repository;

import hello.ch08.domain.Member;
import hello.ch08.domain.Team;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@Slf4j
@Commit
@SpringBootTest
class MemberRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository   teamRepository;

    @Test
    void save() {
        // Team 생성
        Team teamA = new Team();
        teamA.setTeamName("teamA");

        // Member 생서
        Member memberA = new Member();
        memberA.setMemberName("memberA");
        memberA.setTeam(teamA);

        // Team saveTeam = teamRepository.save(teamA);
        // log.info("saveTeam = {}", saveTeam);

        Member saveMember = memberRepository.save(memberA);
        Member findMember = memberRepository.findById(saveMember.getId());
        log.info("saveMember = {}", saveMember);
        log.info("findMember= {}", findMember);

    }
}