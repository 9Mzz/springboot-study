package hello.datajpatest;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import hello.datajpatest.repository.member.MemberRepository;
import hello.datajpatest.repository.team.TeamRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing
@SpringBootApplication
public class DataJpaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataJpaTestApplication.class, args);
    }

    @Bean
    public BeforeInitData beforeInitData(TeamRepository teamRepository, MemberRepository memberRepository) {
        return new BeforeInitData(teamRepository, memberRepository);
    }

    @Bean
    Hibernate5JakartaModule hibernate5JakartaModule() {
        return new Hibernate5JakartaModule();
    }

    @Bean
    AuditorAware<String> auditorProvider() {
        return () -> Optional.of(UUID.randomUUID()
                .toString());
    }

}
