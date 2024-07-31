package hello.order.v3;

import hello.order.OrderService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class OrderServiceV3 implements OrderService {

    private       AtomicInteger stock = new AtomicInteger(100);
    private final MeterRegistry registry;

    public OrderServiceV3(MeterRegistry registry) {
        this.registry = registry;
    }

    @Counted("my.order")
    @Override
    public void order() {

        Timer.builder("my.order")
                .tag("class", this.getClass()
                        .getName())
                .tag("method", "order")
                .description("order")
                .register(registry)
                .record(() -> {
                    log.info("주문");
                    stock.decrementAndGet();
                    sleep(100);
                });
    }


    @Counted("my.order")
    @Override
    public void cancel() {
        Timer.builder("my.order")
                .tag("class", this.getClass()
                        .getName())
                .tag("method", "cancel")
                .description("cancel")
                .register(registry)
                .record(() -> {
                    log.info("주문 취소");
                    stock.incrementAndGet();
                    sleep(50);
                });
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
        return stock;
    }
}
