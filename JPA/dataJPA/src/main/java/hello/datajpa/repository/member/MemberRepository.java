package hello.datajpa.repository.member;

import hello.datajpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findMemberByUserNameAndAgeGreaterThan(String userName, int age);

    List<Member> findTop3HelloBy();

}
