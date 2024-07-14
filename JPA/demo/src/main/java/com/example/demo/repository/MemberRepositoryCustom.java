package com.example.demo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.domain.QMember.member;

@Repository
@Transactional
public class MemberRepositoryCustom {

    private final JPAQueryFactory factory;

    public MemberRepositoryCustom(EntityManager entityManager) {
        this.factory = new JPAQueryFactory(entityManager);
    }

    public void reduceMoney(Long memberId, int totalPrice) {
        factory.update(member)
                .set(member.money, member.money.add(-totalPrice))
                .where(member.id.eq(memberId))
                .execute();
    }

}
