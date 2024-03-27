package hello.ch04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "hello.ch04.web")
public class Ch04Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch04Application.class, args);
    }

}
