package test.testspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.testspring.repository.MemberRepository;
import test.testspring.repository.MemoryMemberRepository;
import test.testspring.service.MemberService;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

}
