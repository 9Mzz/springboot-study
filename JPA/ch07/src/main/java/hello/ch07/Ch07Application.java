package hello.ch07;

import hello.ch07.config.MemberConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Import(MemberConfig.class)
@SpringBootApplication(scanBasePackages = "hello.ch07.web")
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
