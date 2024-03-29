package hello.ch04.repsitory;

import hello.ch04.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final EntityManager entityManager;

    @Override
    public Member save(Member member) {

        entityManager.persist(member);

        return member;
    }

    @Override
    public Member findById(Long id) {
        Member findMember = entityManager.find(Member.class, id);
        return Optional.ofNullable(findMember).orElseThrow();
    }

    @Override
    public List<Member> findAll() {
        String sql = "select m From Member m";
        return entityManager.createQuery(sql, Member.class).getResultList();
    }

    @Override
    public void remove(Long id) {
        Member member = entityManager.find(Member.class, id);
        entityManager.remove(member);
    }

    @Override
    public void update(Long id, Member updateParam) {
        Member findMember = entityManager.find(Member.class, id);


    }
}
