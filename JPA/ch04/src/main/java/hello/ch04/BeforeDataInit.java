package hello.ch04;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

public class BeforeDataInit {

    @EventListener(ApplicationStartedEvent.class)
    public void beforeAct() {

    }

}
