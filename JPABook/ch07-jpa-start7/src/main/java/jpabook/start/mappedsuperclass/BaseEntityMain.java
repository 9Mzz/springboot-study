package jpabook.start.mappedsuperclass;

import jpabook.start.item.Item;
import jpabook.start.item.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class BaseEntityMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager        em  = emf.createEntityManager();
        EntityTransaction    tx  = em.getTransaction();

        try {
            tx.begin();
            //logic
            Member member = new Member();
            member.setName("user");
            member.setCreatedBy("kim");
            member.setCreateDate(LocalDateTime.now());


            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {


        }
        emf.close();
    }


}
