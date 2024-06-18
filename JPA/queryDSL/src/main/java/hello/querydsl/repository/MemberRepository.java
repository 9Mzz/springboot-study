package hello.querydsl.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.querydsl.domain.Member;
import hello.querydsl.domain.condition.MemberSearchCondition;
import hello.querydsl.domain.dto.MemberTeamDto;
import hello.querydsl.domain.dto.QMemberTeamDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.hibernate.query.sqm.mutation.internal.MultiTableSqmMutationConverter;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static hello.querydsl.domain.QMember.member;
import static hello.querydsl.domain.QTeam.team;

@Transactional
@Repository
public class MemberRepository {

    private final EntityManager   em;
    private final JPAQueryFactory factory;

    public MemberRepository(EntityManager em) {
        this.em      = em;
        this.factory = new JPAQueryFactory(em);
    }

    // 등록
    public void save(Member member) {
        em.persist(member);
    }

    // Id 기준 조회
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    // 전체 조회 - JPA Repository
    public List<Member> findAll() {
        String jpql = "select m from Member m";
        return em.createQuery(jpql, Member.class)
                .getResultList();
    }

    // 전체 조회 - queryDSL
    public List<Member> findAll_QueryDSL() {
        return factory.select(member)
                .from(member)
                .fetch();
    }

    // 이름 기준 조회 - JPA Repository
    public List<Member> findByName(String name) {
        String jpql = "select m from Member m where m.userName = :name";
        return em.createQuery(jpql, Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    // 이름 기준 조회 - queryDSL
    public List<Member> findByName_QueryDSL(String name) {
        return factory.select(member)
                .from(member)
                .where(member.userName.contains(name))
                .fetch();
    }

    // 동적 쿼리 - BooleanBuilder 를 사용한 동적 쿼리 해결
    public List<MemberTeamDto> searchByBuilder(MemberSearchCondition condition) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(condition.getUserName())) {
            builder.and(member.userName.contains(condition.getUserName()));
        }
        if (StringUtils.hasText(condition.getTeamName())) {
            builder.and(team.name.contains(condition.getTeamName()));
        }
        if (condition.getAgeGoe() != null) {
            builder.and(member.age.goe(condition.getAgeGoe()));
        }
        if (condition.getAgeLoe() != null) {
            builder.and(member.age.loe(condition.getAgeLoe()));
        }
        return factory.select(new QMemberTeamDto(member.id, member.userName, member.age, team.id, team.name))
                .from(member)
                .leftJoin(member.team, team)
                .where(builder)
                .fetch();
    }

    // 동적 쿼리 - WHERE 절에 다중 파라미터 를 넣는 방법
    public List<MemberTeamDto> searchByWhere(MemberSearchCondition condition) {
        return factory.select(new QMemberTeamDto(member.id, member.userName, member.age, team.id, team.name))
                .from(member)
                .leftJoin(member.team, team)
                .where(memberNameCheck(condition.getUserName()), teamNameCheck(condition.getTeamName()), ageGoeCheck(condition.getAgeGoe()), ageLoeCheck(condition.getAgeLoe()))
                .fetch();
    }

    private BooleanExpression memberNameCheck(String userName) {
        return !StringUtils.hasText(userName) ? null : member.userName.contains(userName);
    }

    private BooleanExpression teamNameCheck(String teamName) {
        return !StringUtils.hasText(teamName) ? null : team.name.contains(teamName);
    }

    private BooleanExpression ageGoeCheck(Integer ageGoe) {
        return ageGoe == null ? null : member.age.goe(ageGoe);
    }

    private BooleanExpression ageLoeCheck(Integer ageLoe) {
        return ageLoe == null ? null : member.age.loe(ageLoe);
    }


}
