package hello.ch05;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Slf4j
@Configuration
public class TestDataInit {


    @EventListener(ApplicationReadyEvent.class)
    public void TestDataInit() {
        log.info("test data start");

    }

}
