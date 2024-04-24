package hello.practice.web;

import hello.practice.domain.Address;
import hello.practice.domain.OrderItem;
import hello.practice.domain.order.Order;
import hello.practice.domain.order.OrderFlatDto;
import hello.practice.domain.order.OrderItemQueryDto;
import hello.practice.domain.order.OrderQueryDto;
import hello.practice.repository.OrderQueryRepository;
import hello.practice.repository.OrderRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.*;

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

    /**
     * V2. 엔티티를 DTO로 변환
     */
    @GetMapping("/api/v2/orders")
    public List<OrderDto> orderV2() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderDto::new)
                .toList();
    }

    @GetMapping("/api/v3/orders")
    public List<OrderDto> orderV3() {
        List<Order> orders = orderRepository.findAllWithItem();
        return orders.stream()
                .map(OrderDto::new)
                .toList();
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV3_page() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return orders.stream()
                .map(OrderDto::new)
                .toList();
    }

    @GetMapping("/api/v3.1.1/orders")
    public List<OrderDto> ordersV3_OffsetLimit(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                               @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<Order> orders = orderRepository.findAllWithMemberDeliveryOffsetLimit(offset, limit);
        return orders.stream()
                .map(OrderDto::new)
                .toList();
    }

    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> orderV4() {
        return orderQueryRepository.findOrderQueryDtos();
    }

    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto> ordersV5() {
        return orderQueryRepository.findAllByDto_optimization();
    }

    @GetMapping("/api/v6/orders")
    public List<OrderQueryDto> ordersV6() {
        List<OrderFlatDto> flats = orderQueryRepository.findAllByDto_flat();

        return null;
/*        return flats.stream()
                .collect(groupingBy(o -> new OrderQueryDto(o.getOrderId(), o.getMemberName(), o.getOrderDate(), o.getStatus(), o.getAddress()),
                        mapping(o -> new OrderItemQueryDto(o.getOrderId(), o.getItemName(), o.getOrderPrice(), o.getCount()), toList())))
                .entrySet()
                .stream()
                .map(e -> new OrderQueryDto(e.getKey()
                        .getOrderId(), e.getKey()
                        .getMemberName(), e.getKey()
                        .getOrderDate(), e.getKey()
                        .getOrderStatus(), e.getKey()
                        .getAddress(), e.getValue()))
                .collect(toList());*/
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
