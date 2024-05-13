package hello.datajpa.config;

import hello.datajpa.repository.team.DataTeamRepository;
import hello.datajpa.repository.team.TeamRepository;
import hello.datajpa.repository.team.TeamRepositoryBasicImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TeamConfig {
    private final DataTeamRepository repository;

    @Bean
    public TeamRepository teamRepository() {
        return new TeamRepositoryBasicImpl(repository);
    }

}
