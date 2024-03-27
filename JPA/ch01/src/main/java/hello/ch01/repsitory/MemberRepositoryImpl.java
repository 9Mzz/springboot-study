package hello.ch01.repsitory;

import hello.ch01.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final EntityManager em;

    @Override
    public Member save(Member member) {

        em.persist(member);

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        Member member = em.find(Member.class, id);

        return Optional.ofNullable(member);
    }

    @Override
    public List<Member> findAll() {

        String jpql = "select m from Member  m ";

        return em.createQuery(jpql, Member.class).getResultList();
    }

    @Override
    public void updateMember(Long id, Member newMember) {
        Member findMember = em.find(Member.class, id);
        findMember.setUsername(newMember.getUsername());
        findMember.setAge(newMember.getAge());


    }

    @Override
    public void deleteMember(Long id) {
        Member findMember = em.find(Member.class, id);
        em.remove(findMember);
    }
}
