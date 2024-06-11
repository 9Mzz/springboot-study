package hello.querydsl.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static hello.querydsl.domain.QHello.*;

@SpringBootTest
@Transactional
@Slf4j
class HelloTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory factory;


    @Test
    void crudTest() {
        factory = new JPAQueryFactory(em);

        Hello basicHello = new Hello();
        em.persist(basicHello);

        Hello resultHello = factory.selectFrom(hello)
                .fetchOne();

        Assertions.assertThat(basicHello)
                .isEqualTo(resultHello);


    }

}