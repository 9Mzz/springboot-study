package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ProductMain {


    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager        em  = emf.createEntityManager();
        EntityTransaction    tx  = em.getTransaction();
        try {
            tx.begin();
            //bm
            oneByOneSave(em);
            oneByOneFind(em);
            findInverse(em);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void oneByOneSave(EntityManager em) {
        Product productA = new Product();
        productA.setName("상품A");
        Product productB = new Product();
        productB.setName("상품B");
        em.persist(productA);
        em.persist(productB);

        Member memberA = new Member();
        memberA.setUsername("회원A");
        memberA.setAge(54);
        //        memberA.getProducts().add(productA);
        memberA.addProduct(productA);
        em.persist(memberA);

    }

    private static void oneByOneFind(EntityManager em) {
        Member        memberA  = em.find(Member.class, 1L);
        List<Product> products = memberA.getProducts();
        for (Product product : products) {
            System.out.println("[list] product = " + product);
        }
    }

    private static void findInverse(EntityManager em) {
        Product      product = em.find(Product.class, 1L);
        List<Member> members = product.getMembers();
        for (Member member : members) {
            System.out.println("member = " + member);
        }
    }
}
