package hello.datajpa.repository.member;

import hello.datajpa.domain.Member;
import hello.datajpa.domain.MemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findMemberByUserNameAndAgeGreaterThan(String userName, int age);

    List<Member> findTop3HelloBy();

    /**
     * @param userName
     * @param userAge
     * @return
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



}
