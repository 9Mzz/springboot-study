package hello.datajpatest;

import hello.datajpatest.domain.Member;
import hello.datajpatest.domain.Team;
import hello.datajpatest.repository.member.MemberRepository;
import hello.datajpatest.repository.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
public class BeforeInitData {

    private final TeamRepository   teamRepository;
    private final MemberRepository memberRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void beforeAct() {
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);

        memberRepository.save(new Member("memberA", 20, teamA));
        memberRepository.save(new Member("memberB", 20, teamA));
        memberRepository.save(new Member("memberC", 20, teamA));
        memberRepository.save(new Member("memberD", 20, teamA));
        memberRepository.save(new Member("memberE", 20, teamA));
        log.info("Before Data End ! ! ! ");
    }

}
