package hello.datajpa.repository.team;

import hello.datajpa.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamDataJPA extends JpaRepository<Team, Long> {
}
