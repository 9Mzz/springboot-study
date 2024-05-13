package hello.datajpa.repository.member;

import hello.datajpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataMemberRepository extends JpaRepository<Member, Long> {
}
