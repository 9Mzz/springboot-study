package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class closeEntityManager {
/*

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabooks");

    public static void main(String[] args) {

        closeEntity();

    }

    private static void closeEntity() {

        EntityManager     emm = emf.createEntityManager();
        EntityTransaction tx  = emm.getTransaction();

        tx.begin();

        Member memberA = new Member("memberA", "testA", 20);
        Member memberB = new Member("memberB", "testB", 20);

        emm.persist(memberA);
        emm.persist(memberB);

        Member findMemberA = emm.find(Member.class, "memberA");
        Member findMemberB = emm.find(Member.class, "memberB");
        System.out.println("findMemberA = " + findMemberA);
        System.out.println("findMemberB = " + findMemberB);

        tx.commit();
        emm.close();
        //        Session/EntityManager is closed
        */
/*
        Member closeMemberA = emm.find(Member.class, "memberA");
        Member closeMemberB = emm.find(Member.class, "memberB");
        System.out.println("closeMemberA = " + closeMemberA);
        System.out.println("closeMemberB = " + closeMemberB);
*//*

    }
*/

}
