package hello.querydsl.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepository {
    private final EntityManager   em;
    private final JPAQueryFactory factory;

    public TestRepository(EntityManager em) {
        this.em      = em;
        this.factory = new JPAQueryFactory(em);
    }
}
