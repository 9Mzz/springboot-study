package hello.springtx.propagation;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    @Transactional
    public void save(Member member) {
        log.info("member 저장");
        em.persist(member);
    }

    public Optional<Member> find(String userName) {
        String jpql = "select m from Member m where m.userName = :userName";
        return em.createQuery(jpql, Member.class)
                .setParameter("userName", userName)
                .getResultList()
                .stream()
                .findAny();
    }

}
