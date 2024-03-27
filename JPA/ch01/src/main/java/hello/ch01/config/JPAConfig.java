package hello.ch01.config;

import hello.ch01.repsitory.MemberRepository;
import hello.ch01.repsitory.MemberRepositoryImpl;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.experimental.WithBy;
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

}
