package hello.datajpatest.repository.member;

import hello.datajpatest.domain.Member;
import hello.datajpatest.domain.dto.MemberDto;
import jakarta.persistence.LockModeType;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.QueryHint;
import org.hibernate.metamodel.model.domain.internal.MapMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DataMemberRepository extends JpaRepository<Member, Long> {

    List<Member> findMemberByMemberNameAndAgeGreaterThan(String name, Integer age);

    @Query("select m from Member m where m.memberName = :memberName")
    List<Member> findMemberByMemberName(@Param("memberName") String memberName);

    @Query("select new hello.datajpatest.domain.dto.MemberDto(m.id, m.memberName, m.age, a.street, a.city, t.teamName) from Member m join m.team t join m.address a")
    List<MemberDto> findMemberDto();

    //
    // Page<Member> findMemberByAge(Integer age, Pageable pageable);

    @Query(value = "select m from Member m join fetch m.team t join fetch m.address a", countQuery = "select count(m) from Member m ")
    Page<Member> findMemberByAge(Integer age, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query(value = "update Member m set m.age = m.age + 1 where m.age >= :memberAge")
    int bulkUpdateAge(@Param("memberAge") int age);

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityQuery();

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByMemberName(String username);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByMemberName(String username);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByAge(int age);

}
