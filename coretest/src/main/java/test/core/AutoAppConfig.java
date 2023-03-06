package test.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import test.core.member.MemberRepository;
import test.core.member.MemberRepositoryImpl;

@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)) public class AutoAppConfig {
    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemberRepositoryImpl();
    }
}