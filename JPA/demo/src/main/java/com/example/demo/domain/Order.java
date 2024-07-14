package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member          member;
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();
    private LocalDateTime   orderDate;
    @Enumerated
    private Status          status;

    public Order(Member member, Status status, LocalDateTime orderDate) {
        this.member    = member;
        this.status    = status;
        this.orderDate = orderDate;
    }

    public static Order createOrder(Member member, OrderItem... orderItems) {
        Order order = new Order(member, Status.READY, LocalDateTime.now());
        for (OrderItem orderItem : orderItems) {
            order.getOrderItems()
                    .add(orderItem);
        }
        return order;
    }
}
