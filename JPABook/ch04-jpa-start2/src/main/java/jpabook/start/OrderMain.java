package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OrderMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager        em  = emf.createEntityManager();
        EntityTransaction    tx  = em.getTransaction();

        try {
            tx.begin();
            //logic
            orderSave(em);
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void orderSave(EntityManager em) {
        //회원 저장
        Members membersA = new Members();
        membersA.setUserName("회원 1");
        em.persist(membersA);

        //상품 저장
        Product productA = new Product();
        productA.setName("상품 1");
        em.persist(membersA);

        //주문 저장
        Order order = new Order();
        order.setOrderMember(membersA);
        order.setOrderProduct(productA);
        order.setCount(2);
        em.persist(order);


    }

}
