package hello.ch10;

import hello.ch10.domain.Address;
import hello.ch10.domain.Member;
import hello.ch10.domain.Order;
import hello.ch10.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Transactional
@SpringBootTest
@ContextConfiguration(classes = Ch10Application.class)
class Ch10ApplicationTests {

    @Autowired
    EntityManager em;

    @Test
    void contextLoads() {

        Team teamA = new Team("teamA");

        Member memberA = new Member("memberA", 20);
        memberA.setTeam(teamA);
        em.persist(memberA);

        Member memberB = new Member("memberB", 42);
        memberB.setTeam(teamA);
        em.persist(memberB);

    }

    @Test
    void EmbeddedTest() {
        Order orderA = new Order(50, new Address("KOREA", "SEOUL", "505_STREET"));
        em.persist(orderA);

        String        query      = "select a From Order  o join o.address a";
        List<Address> resultList = em.createQuery(query, Address.class).getResultList();
        log.info("result = {}", resultList.get(0));
    }

    @Test
    void pagingTest() {
        Team teamA = new Team("teamA");

        for (int i = 0; i <= 20; i++) {
            Member memberS = new Member("testName", new Random().nextInt(30));
            memberS.setTeam(teamA);
            em.persist(memberS);
        }
        String jpql = "select m from  Member m";
        List<Member> resultList = em.createQuery(jpql, Member.class).setFirstResult(5).setMaxResults(10)
                .getResultList();

        for (Member member : resultList) {
            log.info("member = {}", member);
        }
    }

    @Test
    void 집합과_정렬() {
        Team teamA = new Team("teamA");

        for (int i = 0; i <= 20; i++) {
            Member memberS = new Member("testName", new Random().nextInt(30));
            memberS.setTeam(teamA);
            em.persist(memberS);
        }

        String       jpql       = "select t.name, count(m.age), sum(m.age), avg(m.age), max(m.age), min(m.age) From Member m left join m.team t group by t.name ";
        List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();

        log.info("reusltList = {}", resultList);
    }

    @Test
    void fetch_join() {
        Team teamA = new Team("teamA");

        for (int i = 0; i <= 20; i++) {
            Member memberS = new Member("testName", new Random().nextInt(30));
            memberS.setTeam(teamA);
            em.persist(memberS);
        }
        String jpql = "select m From Member m join fetch m.team";
        List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();
        log.info("resultList = {}", resultList);
    }
}
