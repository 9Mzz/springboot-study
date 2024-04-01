package hello.ch08;

import hello.ch08.config.MemberConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Import(MemberConfig.class)
@SpringBootApplication(scanBasePackages = "hello.ch08.web")
public class Ch08Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch08Application.class, args);
    }


    @Bean
    @Profile("local")
    public TestDataInit testDataInit() {
        return new TestDataInit();
    }

}
