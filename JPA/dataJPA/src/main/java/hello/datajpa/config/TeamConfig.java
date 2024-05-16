package hello.datajpa.config;

import hello.datajpa.repository.team.TeamDataJPA;
import hello.datajpa.repository.team.TeamRepository;
import hello.datajpa.repository.team.TeamRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TeamConfig {

    private final TeamDataJPA dataJPA;

    @Bean
    public TeamRepository teamRepository() {
        return new TeamRepositoryImpl(dataJPA);
    }

}
