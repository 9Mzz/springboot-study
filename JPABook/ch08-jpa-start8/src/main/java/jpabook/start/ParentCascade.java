package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ParentCascade {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager        em  = emf.createEntityManager();
        EntityTransaction    tx  = em.getTransaction();
        try {
            tx.begin();
            // bm
            // saveNoCascade(em);
            saveWithCascadeOption(em);

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


    private static void saveNoCascade(EntityManager em) {
        // 부모 저장
        Parent parent = new Parent();
        em.persist(parent);

        // 1번 자식 저장
        Child childA = new Child();
        childA.setParent(parent);
        parent.getChildren().add(childA);
        em.persist(childA);

        // 2번 자식 저장
        Child childB = new Child();
        childB.setParent(parent);
        parent.getChildren().add(childB);
        em.persist(childB);


    }

    private static void saveWithCascadeOption(EntityManager em) {
        // 부모 저장
        Parent parent = new Parent();
        // 1번 자식
        Child childA = new Child();
        // 2번 자식
        Child childB = new Child();

        childA.setParent(parent);
        childB.setParent(parent);

        parent.getChildren().add(childA);
        parent.getChildren().add(childB);
        em.persist(parent);

    }
}
