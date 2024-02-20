package hello.jpa.config;

import hello.jpa.repository.MemberRepository;
import hello.jpa.repository.MemberRepositoryImpl;
import hello.jpa.repository.SpringDataJpaItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JPAConfig {


    private final SpringDataJpaItemRepository repository;

    @Bean
    public MemberRepository memberRepository() {
        return new MemberRepositoryImpl(repository);
    }

}
