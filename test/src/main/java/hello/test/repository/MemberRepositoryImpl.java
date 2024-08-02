package hello.test.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.test.domain.dto.MemberDto;
import hello.test.domain.dto.QMemberDto;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static hello.test.domain.QMember.member;

@Slf4j
@Repository
public class MemberRepositoryImpl {

    private final JPAQueryFactory factory;

    public MemberRepositoryImpl(EntityManager entityManager) {
        this.factory = new JPAQueryFactory(entityManager);
    }

    public MemberDto getMemberByDto(Long memberId) {
        return factory.select(new QMemberDto(member.id, member.memberName, member.memberAddress.city, member.memberAddress.street, member.memberAddress.zipCode))
                .from(member)
                .where(member.id.eq(memberId))
                .fetchOne();
    }
}
