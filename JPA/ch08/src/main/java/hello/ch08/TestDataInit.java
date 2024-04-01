package hello.ch08;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

    @EventListener(ApplicationReadyEvent.class)
    public void testData(){
       log.info("ApplicationReadyEvent.class");
    }

}
