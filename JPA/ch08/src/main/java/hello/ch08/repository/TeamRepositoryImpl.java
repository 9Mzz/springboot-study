package hello.ch08.repository;

import hello.ch08.domain.Team;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Transactional
@Repository
@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamRepository {

    private final EntityManager em;

    @Override
    public Team save(Team team) {
        em.persist(team);
        log.info("save team = {}", team);
        return team;
    }

    @Override
    public Team findById(Long id) {
        return em.find(Team.class, id);
    }

    @Override
    public List<Team> findAll() {
        String     sql        = "select t From Team t";
        List<Team> resultList = em.createQuery(sql, Team.class).getResultList();
        return resultList;
    }
}
