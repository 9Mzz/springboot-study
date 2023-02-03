package test.testspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.testspring.domain.Member;

import java.util.Optional;

public interface SpringDataJPAMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
