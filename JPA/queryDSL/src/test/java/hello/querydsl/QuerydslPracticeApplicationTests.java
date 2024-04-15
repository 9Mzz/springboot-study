package hello.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.querydsl.domain.Hello;
import hello.querydsl.domain.QHello;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static hello.querydsl.domain.QHello.*;

@SpringBootTest
@Transactional
public class QuerydslPracticeApplicationTests {

    @Autowired
    EntityManager em;

    @Test
    void contextLoads() {
        Hello hello = new Hello();
        em.persist(hello);

        JPAQueryFactory query  = new JPAQueryFactory(em);
        QHello          qHello = QHello.hello;
        Hello result = query.selectFrom(qHello)
                .fetchOne();
        Assertions.assertThat(result)
                .isEqualTo(qHello);

    }


}
