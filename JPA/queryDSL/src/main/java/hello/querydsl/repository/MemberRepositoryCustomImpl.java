package hello.querydsl.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.querydsl.domain.condition.MemberSearchCondition;
import hello.querydsl.domain.dto.MemberTeamDto;
import hello.querydsl.domain.dto.QMemberTeamDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static hello.querydsl.domain.QMember.member;
import static hello.querydsl.domain.QTeam.team;

@Repository
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory factory;

    public MemberRepositoryCustomImpl(EntityManager em) {
        this.factory = new JPAQueryFactory(em);
    }

    @Override
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

    @Override
    public List<MemberTeamDto> searchByWhere(MemberSearchCondition condition) {
        return factory.select(new QMemberTeamDto(member.id, member.userName, member.age, team.id, team.name))
                .from(member)
                .leftJoin(member.team, team)
                .where(nameCheck(condition.getUserName()), teamNameCheck(condition.getTeamName()),
                       ageGoeCheck(condition.getAgeGoe()), ageLoeCheck(condition.getAgeLoe()))
                .fetch();
    }

    @Override
    public Page<MemberTeamDto> searchPage(MemberSearchCondition condition, Pageable pageable) {
        List<MemberTeamDto> content = factory.select(
                        new QMemberTeamDto(member.id, member.userName, member.age, team.id, team.name))
                .from(member)
                .leftJoin(member.team, team)
                .where(nameCheck(condition.getUserName()), teamNameCheck(condition.getTeamName()),
                       ageGoeCheck(condition.getAgeGoe()), ageLoeCheck(condition.getAgeLoe()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long total = factory.select(member.count())
                .from(member)
                .leftJoin(member.team, team)
                .where(nameCheck(condition.getUserName()), teamNameCheck(condition.getTeamName()),
                       ageGoeCheck(condition.getAgeGoe()), ageLoeCheck(condition.getAgeLoe()))
                .fetchOne();
        return new PageImpl<>(content, pageable, total);
    }


    private BooleanExpression nameCheck(String userName) {
        return StringUtils.hasText((userName)) ? member.userName.like("%" + userName + "%") : null;
    }

    private BooleanExpression teamNameCheck(String teamName) {
        return StringUtils.hasText(teamName) ? team.name.like("%" + teamName + "%") : null;
    }

    private BooleanExpression ageGoeCheck(Integer ageGoe) {
        return ageGoe != null ? member.age.goe(ageGoe) : null;
    }

    private BooleanExpression ageLoeCheck(Integer ageLoe) {
        return ageLoe != null ? member.age.loe(ageLoe) : null;
    }
}
