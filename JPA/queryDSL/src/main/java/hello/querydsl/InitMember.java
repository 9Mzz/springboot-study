package hello.querydsl;

import hello.querydsl.domain.Member;
import hello.querydsl.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Transactional
@RequiredArgsConstructor
public class InitMember {

    private final EntityManager em;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        //
        for (int i = 0; i < 100; i++) {
            Team selectTeam = i % 2 == 0 ? teamA : teamB;
            em.persist(new Member("member" + i, i, selectTeam));
        }


    }

}
