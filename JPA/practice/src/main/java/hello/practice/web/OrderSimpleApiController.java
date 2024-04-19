package hello.practice.web;

import hello.practice.domain.Address;
import hello.practice.domain.OrderItem;
import hello.practice.domain.OrderSearch;
import hello.practice.domain.OrderStatus;
import hello.practice.domain.order.Order;
import hello.practice.repository.OrderRepository;
import hello.practice.repository.OrderSimpleQueryDto;
import hello.practice.repository.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/*
 * xToOne(ManyToOne, OneToOne)
 * Order
 * Order -> Member (ManyToOne)
 * Order -> Delivery (OneToOne)
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository            orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    /**
     * V1. 엔티티 직접 노출
     * - Hibernate5Module 모듈 등록, LAZY=null 처리
     * - 양방향 관계 문제 발생 -> @JsonIgnore
     */
    @GetMapping("/api/v1/simple-orders")
    public List<Order> orderV1() {
        return orderRepository.findAll();
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> orderV2() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(SimpleOrderDto::new)
                .toList();
    }


    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> orderV3() {
        List<Order> orders = orderRepository.findAllByAddressDelivery();
        return orders.stream()
                .map(SimpleOrderDto::new)
                .toList();
    }


    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> orderV4() {
        return orderSimpleQueryRepository.findOrderDTOs();
    }


    static class SimpleOrderDto {
        private Long        id;
        private String      name;
        private Date        orderDate;
        private OrderStatus status;
        private Address     address;

        public SimpleOrderDto(Order order) {
            this.id        = order.getId();
            this.name      = order.getMember()
                    .getName();
            this.orderDate = order.getOrderDate();
            this.status    = order.getStatus();
            this.address   = order.getDelivery()
                    .getAddress();
        }
    }

}
