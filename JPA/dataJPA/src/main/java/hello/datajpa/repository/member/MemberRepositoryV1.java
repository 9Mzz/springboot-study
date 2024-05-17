package hello.datajpa.repository.member;

import hello.datajpa.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * MemberJpaRepository
 */
@Slf4j
@Transactional
@Repository
@RequiredArgsConstructor
public class MemberRepositoryV1 {

    private final EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public void delete(Member member) {
        em.remove(member);
    }

    public Long count() {
        return em.createQuery("select count(m) from Member m ", Long.class)
                .getSingleResult();
    }

    public List<Member> findByUsernameAndAgeGreaterThen(String userName, Integer age) {
        String jpql = "select m from Member m where m.userName = :userName and m.age >= :userAge";
        return em.createQuery(jpql, Member.class)
                .setParameter("userName", userName)
                .setParameter("userAge", age)
                .getResultList();
    }


}
