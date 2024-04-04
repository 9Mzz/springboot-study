package hello.ch10jpql.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.ch10jpql.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.List;

@Transactional
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final EntityManager   em;
    private final JPAQueryFactory query;

    public MemberRepositoryImpl(EntityManager em) {
        this.em    = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Member save(Member member) {
        return null;
    }

    @Override
    public Member findById(Long id) {
        return null;
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
