package hello.ch01;

import hello.ch01.config.JPAConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;


@Import(JPAConfig.class)
@SpringBootApplication(scanBasePackages = "hello.ch01.web")
public class Ch01Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch01Application.class, args);
    }

}
