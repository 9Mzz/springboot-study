package hello.ch07.config;

import hello.ch07.repository.MemberRepository;
import hello.ch07.repository.MemberRepositoryImpl;
import jakarta.persistence.EntityManager;
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


}
