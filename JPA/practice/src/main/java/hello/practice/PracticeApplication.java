package hello.practice;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import jakarta.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;


// @Import(MemberConfig.class)
@SpringBootApplication
public class PracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }

    @Profile("local")
    @Bean
    public TestDataInit testDataInit(TestDataInit.InitService service) {
        return new TestDataInit(service);
    }

    @Bean
    Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

}
