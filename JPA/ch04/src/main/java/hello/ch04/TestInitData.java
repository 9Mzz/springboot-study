package hello.ch04;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
public class TestInitData {


    @EventListener(ApplicationReadyEvent.class)
    public void beforeInit() {
        log.info("before data start");

    }


}
