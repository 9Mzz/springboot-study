package hello.datajpa.domain;

import hello.datajpa.repository.member.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    EntityManager em;
    @Autowired
    private MemberRepository memberRepository;


    @Test
    void testEntity() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member memberA = new Member("memberA", 20, teamA);
        Member memberB = new Member("memberB", 30, teamA);
        Member memberC = new Member("memberC", 40, teamA);
        Member memberD = new Member("memberD", 50, teamA);
        em.persist(memberA);
        em.persist(memberB);
        em.persist(memberC);
        em.persist(memberD);

        // 초기화
        em.flush();
        em.clear();

        // Check
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        members.forEach(member -> {
            log.info("///////////////");
            log.info("member : {}", member);
            log.info("member.getTeam : {}", member.getTeam());
            log.info("member.getClass : {}", member.getClass());
        });
    }

    @Test
    void JpaEventBaseEntity() throws InterruptedException {
        List<Member> memberCustom = memberRepository.findMemberCustom();

    }

}