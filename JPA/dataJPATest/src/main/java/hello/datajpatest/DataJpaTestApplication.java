package hello.datajpatest;

import hello.datajpatest.config.MemberConfig;
import hello.datajpatest.config.TeamConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({MemberConfig.class, TeamConfig.class})
@SpringBootApplication(scanBasePackages = "hello.datajpatest.web")
public class DataJpaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataJpaTestApplication.class, args);
    }

}
