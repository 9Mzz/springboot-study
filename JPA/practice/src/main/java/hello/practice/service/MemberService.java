package hello.practice.service;

import hello.practice.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
@RequiredArgsConstructor
public class MemberService {

    private final EntityManager em;

    public Member memberSave(Member member) {
        em.persist(member);
        return member;
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public void UpdateMember(Long id, Member member) {
        Member updateParam = findById(id);
        updateParam.setName(member.getName());
    }

}
