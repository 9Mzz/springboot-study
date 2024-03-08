package jpabook.start;

import javax.persistence.*;


public class MemberMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager        em  = emf.createEntityManager();
        EntityTransaction    tx  = em.getTransaction();

        try {
            tx.begin();
            //bm
            userSave(em);
            //            printUser(em);
            printUserAndTeam(em);
            //            printReference(em);
            //            findEquals(em);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
        emf.close();

    }

    private static void userSave(EntityManager em) {

        Team teamA = new Team();
        teamA.setName("팀 A");
        em.persist(teamA);

        Member memberA = new Member();
        memberA.setUsername("회원 1");
        memberA.setAge(52);
        memberA.setTeam(teamA);
        em.persist(memberA);


        //DB에 변동값 저장 후 1차캐시 끊기
        em.flush();
        em.clear();


    }

    private static void printUser(EntityManager em) {
        Member findMember = em.find(Member.class, 1L);
        //        System.out.println("[printUser] 회원 이름 = " + findMember.getUsername());
    }

    private static void printUserAndTeam(EntityManager em) {
        Member findMember = em.find(Member.class, 1L);
        //객체 그래프 탐색
        Team findTeam = findMember.getTeam();
        //Team entity 사용
        System.out.println("[printUserAndTeam] 회원 이름 :  = " + findMember.getUsername());
        System.out.println("[printUserAndTeam] 소속팀 = " + findTeam.getName());
    }

    private static void printReference(EntityManager em) {
        Member findMember = em.getReference(Member.class, 1L);
        findMember.getTeam();

    }

    private static void findEquals(EntityManager em) {
        Member memberA = em.find(Member.class, 1L);
        Member memberB = em.find(Member.class, 1L);
        System.out.println(memberA.getClass() == memberB.getClass());

    }
}
