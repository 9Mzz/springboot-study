package hello.practice.web;

import hello.practice.domain.Order;
import hello.practice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/order/v1/simple-orders")
    public List<Order> orderV1() {
        List<Order> all = orderRepository.findAll();
        return all;
    }

}
