package hello.order.v4;

import hello.order.OrderService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Timed(value = "my.order")
@Slf4j
public class OrderServiceV4 implements OrderService {

    private AtomicInteger store = new AtomicInteger(100);


    @Override
    public void order() {
        log.info("주문");
        store.decrementAndGet();
        sleep(200);
    }

    @Override
    public void cancel() {
        log.info("주문 취소");
        store.incrementAndGet();
        sleep(100);

    }

    private static void sleep(int i) {
        try {
            Thread.sleep(i + new Random().nextInt(1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AtomicInteger getStock() {
        return null;
    }
}
