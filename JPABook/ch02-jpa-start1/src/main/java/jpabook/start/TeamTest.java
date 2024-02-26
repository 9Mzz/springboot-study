package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class TeamTest {

    public static void main(String[] args) {
        EntityManagerFactory emm = Persistence.createEntityManagerFactory("jpabook");
        EntityManager        em  = emm.createEntityManager();
        EntityTransaction    tx  = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            queryLogicJoin(em);
            updateRelation(em);
            deleteRelation(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emm.close();

    }

    private static void logic(EntityManager em) {
        System.out.println("TeamTest.logic");
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);
    }

    private static void queryLogicJoin(EntityManager em) {
        System.out.println("TeamTest.queryLogicJoin");
        String jpql = "select m from Member m join m.team t where t.name = :teamName";

        List<Member> resultList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "회원1")
                .getResultList();

        for (Member member : resultList) {
            System.out.println("[query] Member.userName =  " + member.getUsername());
        }
    }

    private static void updateRelation(EntityManager em) {
        System.out.println("TeamTest.updateRelation");
        Team team2 = new Team("team2", "팀2");
        em.persist(team2);
        Member member1 = em.find(Member.class, "member1");
        member1.setTeam(team2);
    }

    private static void deleteRelation(EntityManager em) {
        System.out.println("TeamTest.deleteRelation");
        Member member1 = em.find(Member.class, "member1");
        Member member2 = em.find(Member.class, "member2");
        member1.setTeam(null);
        member2.setTeam(null);
        em.remove(Team.class);
    }

}
