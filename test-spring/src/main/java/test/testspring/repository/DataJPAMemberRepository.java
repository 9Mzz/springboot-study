package test.testspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.testspring.domain.Member;

import java.util.Optional;

public interface DataJPAMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    Optional<Member> findByName(String name);

}
