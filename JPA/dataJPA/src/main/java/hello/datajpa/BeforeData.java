package hello.datajpa;

import hello.datajpa.domain.Member;
import hello.datajpa.domain.Team;
import hello.datajpa.repository.member.MemberRepository;
import hello.datajpa.repository.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.mvc.method.annotation.RequestAttributeMethodArgumentResolver;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class BeforeData {

    private final MemberRepository memberRepository;
    private final TeamRepository   teamRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void beforeInit() {
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);
        Member memberA = new Member("memberA", 20, teamA);
        Member memberB = new Member("memberB", 20, teamA);
        Member memberC = new Member("memberC", 20, teamA);
        Member memberD = new Member("memberD", 20, teamA);
        Member memberE = new Member("memberE", 20, teamA);
        memberRepository.save(memberA);
        memberRepository.save(memberB);
        memberRepository.save(memberC);
        memberRepository.save(memberD);
        memberRepository.save(memberE);

    }


}
