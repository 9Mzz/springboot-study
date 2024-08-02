package hello.test.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.reflect.IReflectionWorld;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member          member;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private Status        orderStatus;

    // 주문생성
    public static Order createOrder(Member member, OrderItem... orderItems) {
        Order order = new Order(member, LocalDateTime.now(), Status.END);
        for (OrderItem orderItem : orderItems) {
            order.getOrderItems()
                    .add(orderItem);
        }
        return order;
    }

    // 주문취소
    public void cancel() {
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    public Order(Member member, LocalDateTime orderDate, Status orderStatus) {
        this.member      = member;
        this.orderDate   = orderDate;
        this.orderStatus = orderStatus;
    }
}