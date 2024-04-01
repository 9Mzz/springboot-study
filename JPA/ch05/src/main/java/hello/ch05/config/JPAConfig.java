package hello.ch05.config;

import hello.ch05.repository.MemberRepository;
import hello.ch05.repository.MemberRepositoryImpl;
import hello.ch05.repository.TeamRepository;
import hello.ch05.repository.TeamRepositoryImpl;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JPAConfig {

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
