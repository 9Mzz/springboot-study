package hello.practice.web;

import hello.practice.domain.Address;
import hello.practice.domain.OrderStatus;
import hello.practice.domain.order.Order;
import hello.practice.repository.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * API 개발 고급 - 지연 로딩과 조회 성능 최적화
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository repository;

    /**
     * V1. 엔티티 직접 노출
     * - Hibernate5Module 모듈 등록, LAZY=null 처리
     * - 양방향 관계 문제 발생 -> @JsonIgnore
     */
    @GetMapping("/api/v1/simple-order")
    public List<Order> orderV1() {
        return repository.findAll();
    }

    /**
     * LAZY 강제 초기화
     */
    @GetMapping("/api/v1/simple-order/proto")
    public List<Order> orderV1Proto() {
        List<Order> orders = repository.findAll();
        for (Order order : orders) {
            order.getMember()
                    .getName();
            order.getDelivery()
                    .getAddress();
        }
        return orders;
    }

    /**
     * V2. 엔티티를 조회해서 DTO로 변환(fetch join 사용X)
     * - 단점: 지연로딩으로 쿼리 N번 호출
     */
    @GetMapping("/api/v2/simple-order")

    public List<SimpleOrderDto> ordersV2() {
        List<Order> orders = repository.findAll();
        return orders.stream()
                .map(SimpleOrderDto::new)
                .toList();
    }

    @GetMapping("/api/v3/simple-order")
    public List<SimpleOrderDto> ordersV3() {
        List<Order> orders = repository.findAllWithMemberDelivery();
        return orders.stream()
                .map(order -> new SimpleOrderDto(order))
                .toList();
    }

    @Data
    static class SimpleOrderDto {

        private Long        orderId;
        private String      userName;
        private Date        orderDate;
        private OrderStatus status;
        private Address     address;

        public SimpleOrderDto(Order order) {
            this.orderId   = order.getId();
            this.userName  = order.getMember()
                    .getName();
            this.orderDate = order.getOrderDate();
            this.status    = order.getStatus();
            this.address   = order.getMember()
                    .getAddress();
        }
    }

}
