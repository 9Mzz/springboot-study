package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExamMergeMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String[] args) {
        Member member = createMember("memberA", "회원1", 30);
        member.setUsername("회원명 변경");

        mergeMember(member);
    }


    private static Member createMember(String id, String userName, int age) {
        EntityManager     em1 = emf.createEntityManager();
        EntityTransaction tx1 = em1.getTransaction();
        tx1.begin();

        Member member = new Member();
        member.setId(id);
        member.setUsername(userName);
        member.setAge(age);

        em1.persist(member);
        Member findMember1 = em1.find(Member.class, id);
        System.out.println("findMember1 = " + findMember1);

        tx1.commit();
        //엉속성 컨테스트 1 종료, member Entity는 준영속 상태가 된다.
        em1.close();

        return member;
    }

    /**
     * 영속성 컨테스트 2 시작
     */
    private static void mergeMember(Member member) {

        EntityManager     em2 = emf.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();

        tx2.begin();
        //        Member mergeMember = em2.merge(member);
        member = em2.merge(member);

        tx2.commit();

        //준영속 상태
        System.out.println("준영속 상태 member = " + member);

        //영속 상태
        //        System.out.println("영속 상태 mergeMember = " + mergeMember);

        System.out.println("em2 contains member = " + em2.contains(member));
        //        System.out.println("em2 contains mergeMember = " + em2.contains(mergeMember));
    
        em2.close();

    }


}
