package com.example.test;

import com.example.test.domain.Address;
import com.example.test.domain.Member;
import com.example.test.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Random;

@Transactional
@RequiredArgsConstructor
public class DataInit {

    private final EntityManager em;

    @EventListener(ApplicationReadyEvent.class)
    public void beforeInit() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        for (int i = 0; i < 500; i++) {
            Team teamCheck = i % 2 == 0 ? teamA : teamB;
            em.persist(
                    new Member("member" + i, new Random().nextInt(50), new Address("Seoul", "SaDang-" + i), teamCheck));
        }

    }
}
