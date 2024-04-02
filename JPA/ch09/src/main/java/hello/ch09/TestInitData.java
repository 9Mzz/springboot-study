package hello.ch09;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
public class TestInitData {

    @EventListener(ApplicationReadyEvent.class)
    public void testDataInit() {

        log.info("test data");

    }

}
