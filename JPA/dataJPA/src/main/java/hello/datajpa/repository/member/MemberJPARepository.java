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
