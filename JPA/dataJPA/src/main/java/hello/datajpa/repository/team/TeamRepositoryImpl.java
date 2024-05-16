package hello.datajpa.repository.team;

import hello.datajpa.domain.Team;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamRepository {

    private final TeamDataJPA repository;

    @Override
    public Team save(Team team) {
        repository.save(team);
        log.info("save team : {}", team);
        return team;
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
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
