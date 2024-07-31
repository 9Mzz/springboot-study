package hello.order.guage;

import hello.order.OrderService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.NativeQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Slf4j
@Configuration
public class StockConfigV2 {

    @Bean
    public MeterBinder meterBinder(OrderService orderService) {
        return registry -> Gauge.builder("my.stock", orderService, service -> {
                    log.info("stock guage call");
                    return service.getStock()
                            .get();
                })
                .register(registry);
    }

}