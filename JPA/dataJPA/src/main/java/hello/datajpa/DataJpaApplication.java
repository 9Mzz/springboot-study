package hello.datajpa;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import hello.datajpa.repository.member.MemberRepository;
import hello.datajpa.repository.team.TeamRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing
@SpringBootApplication
public class DataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataJpaApplication.class, args);
    }

    @Profile("local")
    @Bean
    public BeforeData beforeData(MemberRepository memberRepository, TeamRepository teamRepository) {
        return new BeforeData(memberRepository, teamRepository);
    }

    @Bean
    public Hibernate5JakartaModule hibernate5JakartaModule() {
        return new Hibernate5JakartaModule();
    }

    @Bean
    AuditorAware<String> auditorProvider() {
        return () -> Optional.of(UUID.randomUUID()
                .toString());
    }

}
