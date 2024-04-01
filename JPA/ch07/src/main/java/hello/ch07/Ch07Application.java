package hello.ch07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Ch07Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch07Application.class, args);
    }


    @Bean
    @Profile("local")
    public TestDataInit testDataInit() {

        return new TestDataInit();
    }


}
