package hello.practice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

    @EventListener(ApplicationReadyEvent.class)
    public void testData() {
        log.info("test Data");
    }
}
