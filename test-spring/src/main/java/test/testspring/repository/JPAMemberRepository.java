package test.testspring.repository;

import test.testspring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JPAMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JPAMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {

        em.persist(member);

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(em.find(Member.class, id));
    }

    @Override
    public Optional<Member> findByName(String name) {

        return em.createQuery("select m from Member m where m.name = :name ", Member.class)
                 .setParameter("name", name)
                 .getResultList().stream().findAny();
    }

    @Override
    public List<Member> findAll() {

        return em.createQuery("select m from Member  m", Member.class)
                 .getResultList();
    }
}
