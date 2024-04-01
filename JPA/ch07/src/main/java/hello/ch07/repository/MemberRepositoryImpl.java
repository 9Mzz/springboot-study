package hello.ch07.repository;

import hello.ch07.mapped.Member;
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
    public void save(Member member) {
        em.persist(member);
        log.info("save data = {}", member);
    }

    @Override
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    @Override
    public List<Member> findAll() {
        String       jpql       = "select m From Member m";
        List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();

        return resultList;
    }

    @Override
    public void delete(Long id) {
        em.remove(id);
    }
}
