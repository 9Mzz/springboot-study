package test.testspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.testspring.repository.JdbcMemberRepository;
import test.testspring.repository.JpaMemberRepository;
import test.testspring.repository.MemberRepository;
import test.testspring.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {

        return new MemberService(repository());
    }

    @Bean
    public MemberRepository repository() {

        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }


}
