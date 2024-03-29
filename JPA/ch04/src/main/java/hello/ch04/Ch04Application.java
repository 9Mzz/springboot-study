package hello.ch04;

import hello.ch04.config.JPAConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Import(JPAConfig.class)
@SpringBootApplication(scanBasePackages = "hello.ch04.web")
public class Ch04Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch04Application.class, args);
    }

    @Bean
    @Profile("local")
    public TestInitData testInitData() {
        return new TestInitData();
    }

}
