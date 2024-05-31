package hello.datajpa.repository.member;

import hello.datajpa.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberJPARepository {

    private final EntityManager em;

    /**
     * 순수 JPA Repository
     */
    public List<Member> findByUsernameAgeGreaterThen(String userName, int age) {
        return em.createQuery("select m from Member m where m.userName >= :userName and m.age >= :age", Member.class)
                .setParameter("userName", userName)
                .setParameter("age", age)
                .getResultList();
    }

    public Long getCount(int i) {
        return em.createQuery("select count(m) from Member m where m.age >= :mAge", Long.class)
                .setParameter("mAge", i)
                .getSingleResult();
    }

    /**
     * 순수 JPA 페이징과 정렬
     */
    public List<Member> findByPage(int age, int offset, int limit) {
        return em.createQuery("select m from Member m where m.age >= :age", Member.class)
                .setParameter("age", age)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    /**
     * 벌크성 수정 쿼리
     */
    public int bulkAgePlus(int age) {
        return em.createQuery("update Member m set m.age = m.age + 1 where m.age >= :mAge")
                .setParameter("mAge", age)
                .executeUpdate();
    }

    // CRUD

    /**
     * Create
     */
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    /**
     * Read
     */
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    /**
     * Update -> 변경감지 사용
     */
    //

    /**
     * Delete
     */
    public void delete(Member member) {
        em.remove(member);
    }

    /**
     * Count
     */
    public Long getCount() {
        return em.createQuery("select count (m) from Member m", Long.class)
                .getSingleResult();
    }
}
