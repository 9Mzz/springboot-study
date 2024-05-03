package hello.datajpa;

import hello.datajpa.config.SpringDataConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import(SpringDataConfig.class)
@SpringBootApplication(scanBasePackages = "hello.datajpa.web")
public class DataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataJpaApplication.class, args);
    }

}
