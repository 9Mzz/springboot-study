package hello.practice.web;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import hello.practice.domain.Address;
import hello.practice.domain.OrderItem;
import hello.practice.domain.order.Order;
import hello.practice.repository.OrderRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

    /**
     * V1. 엔티티 직접 노출
     * - Hibernate5Module 모듈 등록, LAZY=null 처리
     * - 양방향 관계 문제 발생 -> @JsonIgnore
     */
    @GetMapping("/api/v1/orders")
    public List<Order> orderV1() {
        List<Order> all = orderRepository.findAll();
        for (Order order : all) {
            order.getMember()
                    .getName();
            order.getDelivery()
                    .getAddress();
            order.getOrderItems()
                    .stream()
                    .forEach(orderItem -> orderItem.getItem()
                            .getName());
        }
        return all;
    }

    public List<OrderDto> orderV2() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderDto::new)
                .toList();
    }

    @Getter
    @Setter
    static class OrderDto {
        private Long    orderId;
        private String  memberName;
        private Date    orderDate;
        private Address address;

        private List<OrderItemDto> orderItems;

        public OrderDto(Order order) {
            this.orderId    = order.getId();
            this.memberName = order.getMember()
                    .getName();
            this.orderDate  = order.getOrderDate();
            this.address    = order.getDelivery()
                    .getAddress();
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
