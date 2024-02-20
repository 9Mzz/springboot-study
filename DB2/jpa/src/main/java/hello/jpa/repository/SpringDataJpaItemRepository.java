package hello.jpa.repository;

import hello.jpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaItemRepository extends JpaRepository<Member, Long> {
}
