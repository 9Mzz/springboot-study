package hello.hellospring;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
class SpringConfig {

    private MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {

        return new MemberService(memberRepository);
    }

/*    @Bean
    public MemberRepository memberRepository() {
        //Memory 방식
        //return new MemoryMemberRepository();

        //jdbc 방식
        //return new JdbcMemberRepository(dataSource);

        //jdbc template 방식
        //return new JdbcMemberRepository(dataSource);

        //jpa 방식
        return new JpaMemberRepository(em);
    }*/


}
