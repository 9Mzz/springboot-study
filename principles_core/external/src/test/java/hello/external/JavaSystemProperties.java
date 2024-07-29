package hello.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.ClassLoaderAwareGeneratorStrategy;

import java.util.Properties;

@Slf4j
public class JavaSystemProperties {

    public static void main(String[] args) {
        System.setProperty("hello_key", "hello_value");
        String hello_key = System.getProperty("hello_key");
        log.info("key={}", hello_key);

        log.info("==============================================");
        Properties properties = System.getProperties();
        for (Object key : properties.keySet()) {
            log.info("key = {} / value = {}", key, properties.get(key));
        }

        log.info("==============================================");
        String url      = System.getProperty("url");
        String username = System.getProperty("username");
        String password = System.getProperty("password");
        log.info("url = {}", url);
        log.info("username = {}", username);
        log.info("password = {}", password);
    }


}
