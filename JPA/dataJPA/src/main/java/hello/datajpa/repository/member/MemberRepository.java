package hello.datajpa.repository.member;


import hello.datajpa.domain.Member;
import hello.datajpa.domain.MemberDto;
import hello.datajpa.repository.member.custom.MemberRepositoryCustom;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    List<Member> findMemberByUserNameAndAgeGreaterThan(String userName, int age);

    List<Member> findTop3HelloBy();

    /**
     * @param userName
     * @param userAge
     * @Query 어노테이션 사용,
     */
    @Query("select m from Member m where m.userName = :userName and m.age >= :userAge")
    List<Member> findUser(@Param("userName") String userName, @Param("userAge") Integer userAge);

    /**
     * DTO 사용
     */
    @Query("select new hello.datajpa.domain.MemberDto(m.id, m.userName, m.age, a.memberAddress, t.name) from Member m join m.team t join m.address a")
    List<MemberDto> findMemberDto();

    /**
     * 5-1 : 파라미터 바인딩
     */
    @Query("select m from Member m where m.userName = :userName")
    Member findMembers(@Param("userName") String userName);

    /**
     * 5-2 : 컬렉션 파라미터 바인딩
     */
    @Query("select m from Member m where m.userName in :userNames")
    List<Member> findByNames(@Param("userNames") Collection<String> userNames);


    /**
     * 6 : 반환 타입
     */
    List<Member> findListByUserName(String userName);

    Member findMemberByUserName(String userName);

    Optional<Member> findOptionalMemberByUserName(String userName);

    /**
     * 페이징과 정렬을 사용하는 예제
     */
    // 1. Page
    Page<Member> findMemberByAge(int age, Pageable pageable);

    // 2. Sort
    // Slice<Member> findMemberByAge(int age, Pageable pageable);

    /**
     * 스프링 데이터 JPA를 사용한 벌크성 수정 쿼리
     * 벌크성 수정, 삭제 쿼리는 @Modifying 어노테이션을 사용한다.
     *
     * @Modifying -> em.flush() + em.clear()
     */
    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age +1 where m.age >= :mAge")
    int bulkAgePlus(@Param("mAge") int age);

    /**
     * JPQL 페치 조인
     */
    @Query("select m from Member m join fetch m.team t")
    List<Member> findMemberFetchJoin();

    // 공통 메서드 오버라이드
    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    // JPQL + EntityGraph
    // EntityGraph -> fetch join 의 간편 버전, left outer join 사용
    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    @EntityGraph(attributePaths = {"team"})
    List<Member> findEntityGraphByUserName(String userName);

    // NamedEntityGraph -> 잘 사용하지 않음.
    // List<Member> findMemberEntityGraphs();

    /**
     * QueryHint
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUserName(String userName);

    /**
     * Lock
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Member findLockByUserName(String userName);

}
