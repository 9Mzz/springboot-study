package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SpringConfig {

    @Bean
    public MemberService memberService() {

        return new MemberService(memberRepository);
    }

    private MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
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
