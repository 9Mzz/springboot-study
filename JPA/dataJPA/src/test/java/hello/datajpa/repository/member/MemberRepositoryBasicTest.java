package hello.datajpa.repository.member;

import hello.datajpa.domain.Member;
import hello.datajpa.domain.Team;
import hello.datajpa.repository.team.TeamRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.mvc.method.annotation.RequestAttributeMethodArgumentResolver;

@Transactional
@SpringBootTest
class MemberRepositoryBasicTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository   teamRepository;

    @Test
    void CRUDTest() {
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);
        Member memberA = new Member("memberA", 10, teamA);

        Long memberAId = memberRepository.save(memberA);
        Member findMember = memberRepository.findById(memberAId)
                .get();
        Assertions.assertThat(memberA)
                .isEqualTo(findMember);

    }


}