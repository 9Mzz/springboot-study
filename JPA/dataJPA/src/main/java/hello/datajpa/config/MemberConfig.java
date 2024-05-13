package hello.datajpa.config;

import hello.datajpa.repository.member.DataMemberRepository;
import hello.datajpa.repository.member.MemberRepository;
import hello.datajpa.repository.member.MemberRepositoryBasicImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MemberConfig {


    private final DataMemberRepository repository;

    @Bean
    public MemberRepository memberRepository() {
        return new MemberRepositoryBasicImpl(repository);
    }


}
