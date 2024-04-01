package hello.ch08.repository;

import hello.ch08.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.spi.ResultSetReturn;
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
        log.info("saved data = {}", member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        Member findMember = em.find(Member.class, id);
        log.info("find Member = {}", findMember);
        return findMember;
    }

    @Override
    public List<Member> findAll() {
        String       jpql       = "select  m from  Member m";
        List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();
        return resultList;
    }
}
