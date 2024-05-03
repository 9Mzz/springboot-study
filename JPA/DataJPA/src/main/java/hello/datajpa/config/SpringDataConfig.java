package hello.datajpa.config;

import hello.datajpa.repository.MemberRepository;
import hello.datajpa.repository.MemberRepositoryV2;
import hello.datajpa.repository.SpringDataJpaMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringDataConfig {

    private final SpringDataJpaMemberRepository repository;

    @Bean
    public MemberRepository memberRepository() {
        return new MemberRepositoryV2(repository);
    }

}
