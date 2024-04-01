package hello.ch05.repository;

import hello.ch05.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamRepository {

    private final EntityManager em;

    @Override
    public Team save(Team team) {
        em.persist(team);

        return team;
    }

    @Override
    public Team findById(Long id) {
        return null;
    }

    @Override
    public List<Team> findAll() {
        return null;
    }
}
