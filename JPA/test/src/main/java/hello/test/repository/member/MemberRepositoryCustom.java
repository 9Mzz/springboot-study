package hello.test.repository.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import static hello.test.domain.QMember.member;

@Repository
public class MemberRepositoryCustom {

    private final JPAQueryFactory factory;

    public MemberRepositoryCustom(EntityManager entityManager) {
        this.factory = new JPAQueryFactory(entityManager);
    }

    public long updateMemberMoney(int money) {
        return factory.update(member)
                .set(member.money, money)
                .execute();
    }
}
