package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExamMergeMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void main(String[] args) {
        Member member = createMember("memberA", "회원", 40);
        //준영속 상태에서 변경
        member.setUsername("회원명변경");
        mergeMember(member);

    }

    private static Member createMember(String id, String userName, int age) {
        EntityManager     em1 = emf.createEntityManager();
        EntityTransaction tx  = em1.getTransaction();

        tx.begin();
        /**
         * logic
         */
        Member member = new Member(id, userName, age);

        em1.persist(member);
        tx.commit();
        //영속성 컨테스트 1 종료
        //Member Entity는 준영속 상태가 된다.
        em1.close();
        System.out.println("createMember member = " + member);
        return member;
    }

    private static void mergeMember(Member member) {
        EntityManager     em2 = emf.createEntityManager();
        EntityTransaction tx  = em2.getTransaction();

        tx.begin();
        //Logic
        Member mergeMember = em2.merge(member);
        //        member = em2.merge(member);
        tx.commit();

        System.out.println("mergeMember member = " + member);       //준영속 상태
        System.out.println("mergeMember mergeMember = " + mergeMember);  //영속 상태

        System.out.println("mergeMember contain member = " + em2.contains(member));
        System.out.println("mergeMember contain mergeMember = " + em2.contains(mergeMember));
        em2.close();
    }


}
