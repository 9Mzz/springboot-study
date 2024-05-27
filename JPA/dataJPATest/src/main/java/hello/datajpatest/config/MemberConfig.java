package hello.datajpatest.config;

import hello.datajpatest.repository.member.DataMemberRepository;
import hello.datajpatest.repository.member.MemberRepository;
import hello.datajpatest.repository.member.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MemberConfig {

    private final DataMemberRepository dataMemberRepository;

    @Bean
    MemberRepository memberRepository() {
        return new MemberRepositoryImpl(dataMemberRepository);
    }

}
