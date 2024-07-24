package hello.aop;

import hello.aop.order.OrderRepository;
import hello.aop.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class AopTest {

    @Autowired
    OrderService    orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo() {
        log.info("isAopProxy, OrderService = {}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, OrderRepository = {}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success() {
        String itemId = "itemId";
        orderService.orderItem(itemId);
    }

    @Test
    void exception() {
        String itemId = "ex";
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> orderService.orderItem(itemId))
                .isInstanceOf(IllegalStateException.class);
    }

}
