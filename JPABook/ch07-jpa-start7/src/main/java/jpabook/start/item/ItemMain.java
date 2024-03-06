package jpabook.start.item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ItemMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager        em  = emf.createEntityManager();
        EntityTransaction    tx  = em.getTransaction();

        try {
            tx.begin();
            //logic
//            movieSave(em);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {


        }
        emf.close();
    }

    private static void movieSave(EntityManager em) {
        Movie movie = new Movie();
        movie.setDirector("aaa");
        movie.setActor("bbbb");
        movie.setName("바람과 함께 사라지다");
        movie.setPrice(10000);

        em.persist(movie);
        em.flush();
        em.clear();

        Item findItem = em.find(Movie.class, movie.getId());
        System.out.println("movie = " + findItem);
    }

}
