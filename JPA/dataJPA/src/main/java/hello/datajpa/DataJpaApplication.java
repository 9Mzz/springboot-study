package hello.datajpa;

import hello.datajpa.config.MemberConfig;
import hello.datajpa.config.TeamConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import({MemberConfig.class, TeamConfig.class})
@SpringBootApplication(scanBasePackages = "hello.datajpa.web")
public class DataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataJpaApplication.class, args);
    }

}
