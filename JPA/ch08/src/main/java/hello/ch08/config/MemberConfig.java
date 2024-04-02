package hello.ch08.config;

import hello.ch08.repository.MemberRepository;
import hello.ch08.repository.MemberRepositoryImpl;
import hello.ch08.repository.TeamRepository;
import hello.ch08.repository.TeamRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MemberConfig {

    private final EntityManager em;

    @Bean
    public MemberRepository memberRepository() {
        return new MemberRepositoryImpl(em);
    }

    @Bean
    public TeamRepository teamRepository() {
        return new TeamRepositoryImpl(em);
    }

}
