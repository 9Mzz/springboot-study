package hello.datajpa.repository.team;

import hello.datajpa.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataTeamRepository extends JpaRepository<Team, Long> {
}
