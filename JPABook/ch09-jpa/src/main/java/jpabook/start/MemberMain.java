package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MemberMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager        em  = emf.createEntityManager();
        EntityTransaction    tx  = em.getTransaction();

        try {
            tx.begin();
            //logic
            //            cloneFailTest(em);
            //            cloneTest(em);
            equalTest(em);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();

        } finally {
            em.close();
        }
        emf.close();


    }

    private static void cloneFailTest(EntityManager em) {
        Address address = new Address("city", "street", "10000");

        Member member1 = new Member();
        member1.setName("member1");
        member1.setAddress(address);
        em.persist(member1);

        Member member2 = new Member();
        member2.setName("member2");
        member2.setAddress(address);
        em.persist(member2);

        member2.getAddress()
                .setCity("newCity");
    }

    private static void cloneTest(EntityManager em) {
        Address address = new Address("city", "street", "10000");

        Member member1 = new Member();
        member1.setName("member1");
        member1.setAddress(address);
        em.persist(member1);

        Address cloneAddress = new Address(address.getStreet(), address.getCity(), address.getState());

        Member member2 = new Member();
        member2.setName("member2");
        member2.setAddress(cloneAddress);
        em.persist(member2);

        member2.getAddress()
                .setCity("newCity");


    }

    private static void equalTest(EntityManager em) {
        int a = 10;
        int b = 10;
        System.out.println(" a == b  = " + (a == b));

        Address addressA = new Address("city", "street", "100000");
        Address addressB = new Address("city", "street", "100000");

        System.out.println(" addressA == addressB = " + (addressA == addressB));
        System.out.println(" addressA equals addressB = " + (addressA.equals(addressB)));


    }
}
