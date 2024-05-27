package hello.datajpatest.config;

import hello.datajpatest.domain.Team;
import hello.datajpatest.repository.team.DataTeamRepository;
import hello.datajpatest.repository.team.TeamRepository;
import hello.datajpatest.repository.team.TeamRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TeamConfig {

    private final DataTeamRepository teamRepository;

    @Bean
    TeamRepository teamRepository() {
        return new TeamRepositoryImpl(teamRepository);
    }

}
