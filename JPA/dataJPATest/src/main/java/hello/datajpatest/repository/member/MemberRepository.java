package hello.datajpatest.repository.member;

import hello.datajpatest.domain.Member;
import hello.datajpatest.domain.dto.MemberDto;
import hello.datajpatest.repository.team.TeamRepositoryCustom;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, TeamRepositoryCustom {

    Member findByMemberNameAndAgeGreaterThanEqual(String memberName, Integer age);

    @Query("select m from Member m where m.memberName = :memberName and m.age >= :age")
    List<Member> findUser(@Param("memberName") String memberName, @Param("age") Integer age);

    @Query("select new hello.datajpatest.domain.dto.MemberDto(m.id, m.memberName, m.age, t.teamName) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.memberName in :names")
    List<Member> findCollection(@Param("names") Collection<String> names);

    Page<Member> findByAge(Integer age, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") Integer age);

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m ")
    List<Member> entityGraph();

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    @Query("select m from Member m where m.memberName = :memberName")
    Member queryHint(@Param("memberName") String memberName);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
Member findByMemberName(String memberName);

}
