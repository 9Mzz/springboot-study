package hello.ch10jpql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Ch10jpqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch10jpqlApplication.class, args);
    }

    @Bean
    @Profile("local")
    public TestDataInit testDataInit() {
        return new TestDataInit();
    }

}
