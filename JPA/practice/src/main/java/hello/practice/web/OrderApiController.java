package hello.practice.web;

import hello.practice.domain.*;
import hello.practice.domain.order.Order;
import hello.practice.repository.OrderRepository;
import hello.practice.repository.OrderSimpleQueryDto;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository repository;
    private final OrderRepository orderRepository;

    /**
     * V1. 엔티티 직접 노출
     * - Hibernate5Module 모듈 등록, LAZY=null 처리
     * - 양방향 관계 문제 발생 -> @JsonIgnore
     */
    @GetMapping("/api/v1/orders")
    public List<Order> orderV1() {
        List<Order> all = repository.findAll();
        for (Order order : all) {
            order.getMember()
                    .getName();     // LAZY 강제 초기화
            order.getDelivery()
                    .getAddress();  // LAZY 강제 초기화
            order.getOrderItems()
                    .stream()
                    .forEach(orderItem -> orderItem.getItem()
                            .getName());
        }
        return all;
    }

    /**
     * V2. 엔티티를 DTO로 변환
     */
    @GetMapping("/api/v2/orders")
    public List<OrderDto> orderV2() {
        List<Order> orders = repository.findAll();
        return orders.stream()
                .map(OrderDto::new)
                .toList();
    }

    @GetMapping("/api/v3/orders")
    public List<OrderDto> orderV3() {
        List<Order> orders = repository.findAllWithItem();
        return orders.stream()
                .map(OrderDto::new)
                .toList();
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> orderV3_page(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                       @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<Order> orders = orderRepository.findallwithMemberDelivery(offset, limit);
        return orders.stream()
                .map(OrderDto::new)
                .toList();
    }


    @Getter
    static class OrderDto {
        private Long               orderId;
        private String             memberName;
        private Address            address;
        private Date               orderDate;
        private OrderStatus        status;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order order) {
            this.orderId    = order.getId();
            this.memberName = order.getMember()
                    .getName();
            this.address    = order.getMember()
                    .getAddress();
            this.orderDate  = order.getOrderDate();
            this.status     = order.getStatus();
            this.orderItems = order.getOrderItems()
                    .stream()
                    .map(OrderItemDto::new)
                    .toList();
        }
    }

    @Getter
    static class OrderItemDto {
        private String itemName;
        private int    orderPrice;
        private int    count;

        public OrderItemDto(OrderItem orderItem) {
            this.itemName   = orderItem.getItem()
                    .getName();
            this.orderPrice = orderItem.getOrderPrice();
            this.count      = orderItem.getCount();
        }
    }
}
