package hello;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class EnvironmentCheck {

    private final Environment env;

    public EnvironmentCheck(Environment env) {
        this.env = env;
    }

    @PostConstruct
    public void init() {
        List<String> activeProfiles1 = List.of(env.getDefaultProfiles());
        for (String s : activeProfiles1) {
            log.info("s = {}", s);
        }

        String url      = env.getProperty("url");
        String username = env.getProperty("my_username");
        String password = env.getProperty("password");

        log.info("env url = {}", url);
        log.info("env my_username = {}", username);
        log.info("env password = {}", password);

    }
}


