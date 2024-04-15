package hello.querydsl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
public class TestDataInit {

    @EventListener(ApplicationReadyEvent.class)
    public void testData() {
        log.info("Application Start");
    }

}
