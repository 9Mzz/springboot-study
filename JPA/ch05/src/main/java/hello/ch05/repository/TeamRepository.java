package hello.ch05.repository;

import hello.ch05.domain.Team;

import java.util.List;

public interface TeamRepository {

    Team save(Team team);

    Team findById(Long id);

    List<Team> findAll();

}
