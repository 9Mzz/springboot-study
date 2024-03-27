package hello.ch01;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

public class TestInit {

    @EventListener(ApplicationReadyEvent.class)
    public void testInit() {
    }


}
