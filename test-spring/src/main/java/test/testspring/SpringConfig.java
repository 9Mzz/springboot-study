package test.testspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import test.testspring.repository.MemberRepository;
import test.testspring.service.MemberService;

@Configuration
public class SpringConfig {


    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

/*
    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();

        return new JPAMemberRepository(em);
    }
*/


}
