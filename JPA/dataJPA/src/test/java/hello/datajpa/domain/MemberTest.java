package hello.datajpa.domain;

import jakarta.persistence.EntityManager;
import jakarta.transaction.InvalidTransactionException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.thymeleaf.engine.TemplateHandlerAdapterMarkupHandler;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    EntityManager em;

    @Test
    void testEntity() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);

        Member memberA = new Member("memberA", 20, teamA);
        Member memberB = new Member("memberB", 20, teamA);
        Member memberC = new Member("memberC", 20, teamA);

        em.persist(memberA);
        em.persist(memberB);
        em.persist(memberC);
        em.flush();
        em.clear();

        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        for (Member member : members) {
            log.info("member = {}", member);
            log.info("{} = {}", member.getUserName(), member.getStartDate());
        }

    }

}