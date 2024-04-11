package hello.practice.domain;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Transactional
@SpringBootTest
@Slf4j
class ItemTest {

    @Autowired
    EntityManager em;

    @Test
    void typeTest() {
        Book  book  = new Book("itemA", 5000, "autorA", 3000);
        Movie movie = new Movie("itemB", 12000, "바람과 함께 사라지다", "몰라");

        em.persist(book);
        em.persist(movie);
        em.flush();
        em.clear();

        // type
        String jpql = "select i from Item i where type(i) in (Book)";
        List<Item> resultList = em.createQuery(jpql, Item.class)
                .getResultList();
        for (Item book1 : resultList) {
            log.info("book List = {}", book1);
        }
    }


}