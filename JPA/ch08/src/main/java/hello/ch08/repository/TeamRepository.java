package hello.ch08.repository;

import hello.ch08.domain.Team;

import java.util.List;

public interface TeamRepository {

    Team save(Team team);

    Team findById(Long id);

    List<Team> findAll();

}
