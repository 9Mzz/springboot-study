package hello.ch09;

import hello.ch09.config.MemberConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Import(MemberConfig.class)
@SpringBootApplication(scanBasePackages = "hello.ch09.web")
public class Ch09Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch09Application.class, args);
    }

    @Bean
    @Profile("local")
    public TestInitData testInitData() {
        return new TestInitData();
    }

}
