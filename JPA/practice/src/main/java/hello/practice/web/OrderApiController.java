package hello.practice.web;

import hello.practice.domain.Address;
import hello.practice.domain.OrderItem;
import hello.practice.domain.order.Order;
import hello.practice.repository.OrderRepository;
import hello.practice.repository.order.OrderFlatDto;
import hello.practice.repository.order.OrderQueryDto;
import hello.practice.repository.order.OrderQueryRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository      orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    /**
     * V1. 엔티티 직접 노출
     * - Hibernate5Module 모듈 등록, LAZY=null 처리
     * - 양방향 관계 문제 발생 -> @JsonIgnore
     */
    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAll();

        for (Order order : all) {
            order.getMember()
                    .getName();    // Lazy 강제 초기화
            order.getDelivery()
                    .getAddress();   // Lazy 강제 초기화
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.forEach(o -> o.getItem()
                    .getName());    // Lazy 강제 초기화
        }
        return all;
    }

    /**
     * V2. 엔티티를 DTO로 변환
     */
    @GetMapping("/api/v2/orders")
    public List<OrderDto> orderV2() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> new OrderDto(order))
                .toList();
    }

    @GetMapping("/api/v3/orders")
    public List<OrderDto> orderV3() {
        List<Order> orders = orderRepository.findAllWithItem();
        return orders.stream()
                .map(order -> new OrderDto(order))
                .toList();
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> orderV3_page() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return orders.stream()
                .map(OrderDto::new)
                .toList();
    }

    @GetMapping("/api/v3.2/orders")
    public List<OrderDto> orderV3_Paging(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                         @RequestParam(value = "limit", defaultValue = "100") int limit) {
        return orderRepository.findAllWithMemberDeliveryLimitOffset(offset, limit)
                .stream()
                .map(OrderDto::new)
                .toList();
    }

    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> ordersV4() {
        return orderQueryRepository.findOrderQueryDtos();
    }

    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto> orderV5() {
        return orderQueryRepository.findAllByDto_optimization();
    }


    @GetMapping("/api/v6/orders")
    public List<OrderFlatDto> orderV6() {
        return orderQueryRepository.findAllByDto_flat();
    }

    @Getter
    static class OrderDto {
        private Long               orderId;
        private String             memberName;
        private Date               orderDate;
        private Address            address;
        private List<orderItemDto> orderItems;

        public OrderDto(Order order) {
            this.orderId    = order.getId();
            this.memberName = order.getMember()
                    .getName();
            this.orderDate  = order.getOrderDate();
            this.address    = order.getMember()
                    .getAddress();
            this.orderItems = order.getOrderItems()
                    .stream()
                    .map(orderItemDto::new)
                    .toList();
        }
    }

    @Getter
    static class orderItemDto {
        private String itemName;
        private int    orderPrice;
        private int    count;

        public orderItemDto(OrderItem orderItem) {
            this.itemName   = orderItem.getItem()
                    .getName();
            this.orderPrice = orderItem.getOrderPrice();
            this.count      = orderItem.getCount();
        }
    }
}
