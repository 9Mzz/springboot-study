package hello.ch05;

import hello.ch05.config.JPAConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Import(JPAConfig.class)
@SpringBootApplication(scanBasePackages = "hello.ch05.web")
public class Ch05Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch05Application.class, args);
    }


    @Bean
    @Profile("local")
    public TestDataInit testDataInit() {

        return new TestDataInit();
    }

}
