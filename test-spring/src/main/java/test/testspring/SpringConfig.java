package test.testspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.testspring.repository.JPAMemberRepository;
import test.testspring.repository.MemberRepository;
import test.testspring.repository.MemoryMemberRepository;
import test.testspring.service.MemberService;

import javax.persistence.AttributeOverride;
import javax.persistence.EntityManager;

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

    /*@Bean
    public MemberRepository repository() {

//        return new MemoryMemberRepository();
//        return new JPAMemberRepository(em);


    }*/



}
