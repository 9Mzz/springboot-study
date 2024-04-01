package hello.ch05.repository;

import hello.ch05.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Transactional
@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {


    private final EntityManager em;

    @Override
    public Member save(Member member) {
        em.persist(member);
        log.info("member = {}", member);
        return member;
    }

    @Override
    public Member findById(Long id) {

        return em.find(Member.class, id);
    }

    @Override
    public List<Member> findAll() {
        String jpql = "select m From Members m";
        return em.createQuery(jpql, Member.class).getResultList();
    }

    @Override
    public void memberRemove(Long id) {
        String jpql = "delete from Members m where m.id = :mId";
        em.createQuery(jpql).setParameter("mId", id);
    }
}
