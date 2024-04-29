package hello.practice.web;

import hello.practice.domain.Address;
import hello.practice.domain.OrderItem;
import hello.practice.domain.OrderStatus;
import hello.practice.domain.order.Order;
import hello.practice.repository.OrderQueryRepository;
import hello.practice.repository.OrderRepository;
import hello.practice.repository.OrderSimpleQueryDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

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
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            order.getMember()
                    .getName();
            order.getDelivery()
                    .getAddress();
            order.getOrderItems()
                    .stream()
                    .forEach(orderItem -> orderItem.getItem()
                            .getName());
        }
        return orders;
    }

    /**
     * V2. 엔티티를 DTO로 변환
     */
    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderDto::new)
                .toList();
    }

    /**
     * V3. 엔티티를 DTO로 변환 - 페치 조인 최적화
     */
    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithItem();
        return orders.stream()
                .map(OrderDto::new)
                .toList();
    }

    /**
     * V3.1: 엔티티를 DTO로 변환 - 페이징과 한계 돌파 (이 방식을 선호, 중요)
     */
    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV3_page() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return orders.stream()
                .map(order -> new OrderDto(order))
                .toList();
    }

    /**
     * V3.1.1 엔티티를 조회해서 DTO로 변환 페이징 고려
     * - ToOne 관계만 우선 모두 페치 조인으로 최적화
     * - 컬렉션 관계는 hibernate.default_batch_fetch_size, @BatchSize로 최적화
     */
    @GetMapping("/api/v3.1.1/orders")
    public List<OrderDto> ordersV3_page(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                        @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<Order> orders = orderRepository.findAllWithMemberDeliveryOffsetLimit(offset, limit);
        return orders.stream()
                .map(OrderDto::new)
                .toList();
    }

    @GetMapping("/api/v4/orders")
    public List<OrderDto> ordersV4() {
        orderQueryRepository.findOrderQueryDtos();


    }


    @Getter
    @Setter
    @NoArgsConstructor
    static class OrderDto {

        private Long        orderId;
        private String      memberName;
        private Date        orderDate;
        private OrderStatus status;
        private Address     address;

        private List<OrderItemDto> orderItems;

        public OrderDto(Order order) {
            this.orderId    = order.getId();
            this.memberName = order.getMember()
                    .getName();
            this.orderDate  = order.getOrderDate();
            this.status     = order.getStatus();
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
        private String memberName;
        private int    orderPrice;
        private int    count;

        public OrderItemDto(OrderItem orderItem) {
            this.memberName = orderItem.getItem()
                    .getName();
            this.orderPrice = orderItem.getOrderPrice();
            this.count      = orderItem.getCount();
        }
    }


}
