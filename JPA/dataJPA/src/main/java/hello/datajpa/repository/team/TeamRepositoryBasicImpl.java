package hello.datajpa.repository.team;

import hello.datajpa.domain.Team;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class TeamRepositoryBasicImpl implements TeamRepository {

    private final DataTeamRepository repository;


    @Override
    public Long save(Team team) {
        repository.save(team);
        return team.getId();
    }

    @Override
    public Optional<Team> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Team> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
