package hello.datajpatest.domain;

import hello.datajpatest.repository.member.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@SpringBootTest
@Rollback(value = false)
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;


    @Test
    void testEntity() {
        Team teamA = new Team("teamA");
        em.persist(teamA);
        Member memberA = new Member("memberA", 20, teamA);
        Member memberB = new Member("memberB", 20, teamA);
        Member memberC = new Member("memberC", 20, teamA);
        Member memberD = new Member("memberD", 20, teamA);
        em.persist(memberA);
        em.persist(memberB);
        em.persist(memberC);
        em.persist(memberD);

        em.flush();
        em.clear();

        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        for (Member member : members) {
            log.info("members.getClass = {}", member.getClass());
            // log.info("members = {}", member);
            // log.info("members.getTeam = {}", member.getTeam());
        }
    }

    @Test
    void JPAEventBaseEntity() throws InterruptedException {
        Team teamA = new Team("teamA");
        em.persist(teamA);
        Member memberA = new Member("memberA", 20, teamA);
        memberRepository.save(memberA);
        Thread.sleep(10000);
        memberA.setMemberName("dudududududu");
        em.flush();
        em.clear();

        Member findMember = memberRepository.findById(1L)
                .get();
        log.info("findMember.getCreateBy = {}", findMember.getCreateBy());
        log.info("findMember.getCreatedDate = {}", findMember.getCreatedDate());
        log.info("findMember.getLastModifiedBy = {}", findMember.getLastModifiedBy());
        log.info("findMember.getLastModifiedDate = {}", findMember.getLastModifiedDate());


    }

}