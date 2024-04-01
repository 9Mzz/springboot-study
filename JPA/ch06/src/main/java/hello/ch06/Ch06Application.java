package hello.ch06;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication(scanBasePackages = "hello.ch06.web")
public class Ch06Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch06Application.class, args);
    }


    @Bean
    @Profile("local")
    public TestDataInit testDataInit() {
        return new TestDataInit();
    }

}
