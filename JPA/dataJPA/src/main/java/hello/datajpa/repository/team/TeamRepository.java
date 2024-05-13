package hello.datajpa.repository.team;

import hello.datajpa.domain.Team;

import java.util.List;
import java.util.Optional;

public interface TeamRepository {

    Long save(Team team);

    Optional<Team> findById(Long id);

    List<Team> findAll();

    void delete(Long id);

}
