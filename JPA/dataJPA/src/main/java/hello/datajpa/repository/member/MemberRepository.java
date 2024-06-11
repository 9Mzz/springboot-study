package hello.datajpa.repository.member;

import hello.datajpa.domain.Member;
import hello.datajpa.domain.dto.MemberDto;
import hello.datajpa.domain.dto.UserNameOnlyDto;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUserNameAndAgeGreaterThan(String userName, int age);

    /**
     * Repository method에 쿼리 정의
     */
    @Query("select m from Member m where m.userName = :userName and m.age = :age")
    List<Member> findUser(@Param("userName") String userName, @Param("age") int age);

    /**
     * 값 하나 조회
     */
    @Query("select m.userName from Member m")
    List<String> findUserNameList();

    /**
     * DTO 조회
     */
    @Query("select new hello.datajpa.domain.dto.MemberDto(m.id, m.userName, m.age, t.name) from Member m join m.team t")
    List<MemberDto> findMemberByDto();

    /**
     * 컬렉션 파라미터 바인딩
     */
    @Query("select m from Member m where m.userName in :names")
    List<Member> findByNames(@Param("names") Collection<String> userNames);

    /**
     * 페이징과 정렬 사용
     */
    Page<Member> findByUserName(String userName, Pageable pageable);
    // Slice<Member> findByUserName(String UserNameOnly, Pageable pageable);
    // List<Member> findByUserName(String UserNameOnly, Pageable pageable);
    // List<Member> findByUserName(String UserNameOnly, Sort sort);

    Page<Member> findByAge(int age, Pageable pageable);


    /**
     * 벌크성 수정 쿼리
     */
    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age + 1 where m.age >= :mAge")
    int bulkAgePlus(@Param("mAge") int age);

    /**
     * EntityGraph
     * fetch 의 축소판
     */
    @Query("select m from Member m join fetch m.team t")
    List<Member> findMemberFetchjoin();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m where m.userName = :userName")
    List<Member> findMemberByUserNameEntityGraph(@Param("userName") String userName);


    /**
     * QueryHint
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findQueryHintByUserName(String userName);

    /**
     * Lock
     */
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUserName(String userName);

    /**
     * Projection
     */
    List<UserNameOnly> findProjectionByUserName(String userName);

}

