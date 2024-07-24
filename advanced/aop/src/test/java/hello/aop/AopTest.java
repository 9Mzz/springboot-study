package hello.aop;


import hello.aop.order.OrderRepository;
import hello.aop.order.OrderService;
import hello.aop.order.aop.AspectV3;
import hello.aop.order.aop.AspectV4PointCut;
import hello.aop.order.aop.AspectV5Order;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
// @Import(AspectV1.class)
// @Import(AspectV2.class)
// @Import(AspectV3.class)
// @Import(AspectV4PointCut.class)
@Import({AspectV5Order.LogAspect.class, AspectV5Order.TxAspect.class})
@SpringBootTest
class AopTest {

    @Autowired
    OrderService    orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo() {
        log.info("isAopProxy, OrderService = {}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, OrderRepository = {}", AopUtils.isAopProxy(orderService));
    }

    @Test
    void success() {
        String itemId = "itemA";
        orderService.orderItem(itemId);
    }

    @Test
    void exception() {
        String itemId = "ex";

        Assertions.assertThatThrownBy(() -> orderService.orderItem(itemId))
                .isInstanceOf(IllegalStateException.class);
    }

}
