package hello.datajpa.config;


import hello.datajpa.repository.member.MemberDataJPA;
import hello.datajpa.repository.member.MemberRepository;
import hello.datajpa.repository.member.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MemberConfig {

    private final MemberDataJPA dataJPA;

    @Bean
    public MemberRepository memberRepository() {
        return new MemberRepositoryImpl(dataJPA);
    }

}


