package hello.datajpa.repository.member;

import hello.datajpa.domain.Member;
import jakarta.persistence.EntityManager;
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
    private final MemberRepository memberRepository;

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

    public List<Member> findByUsernameAndAgeGreaterThen(String userName, int age) {
        return em.createQuery("select m from Member m where userName = :userName and :age >= age ", Member.class)
                .setParameter("userName", userName)
                .setParameter("age", age)
                .getResultList();
    }

    /**
     * JPA 페이징 리포지토리
     */
    public List<Member> findByPage(int age, int offset, int limit) {
        return em.createQuery("select m from Member m where m.age >= :age", Member.class)
                .setParameter("age", age)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public Long totalCount(int age) {
        return em.createQuery("select count(m) from Member m where m.age >= :age", Long.class)
                .setParameter("age", age)
                .getSingleResult();
    }

    /**
     * 벌크성 수정 쿼리
     */

    public int bulkAgePlus(int age) {
        return em.createQuery("update Member m set m.age = m.age + 1 where m.age >= :mAge")
                .setParameter("mAge", age)
                .executeUpdate();


    }


}
