package hello.practice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hello.practice.config.queryDSLConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Import(queryDSLConfig.class)
@SpringBootApplication(scanBasePackages = "hello.practice.web")
public class PracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }

    @Profile("local")
    @Bean
    public TestDataInit testDataInit() {
        return new TestDataInit();
    }

}
