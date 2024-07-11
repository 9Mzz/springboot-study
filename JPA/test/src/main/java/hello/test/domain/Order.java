package hello.test.domain;

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
    private Long          id;
    private LocalDateTime orderDate;
    private Status        orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member          member;
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItemList = new ArrayList<>();

    public static Order createOrder(Member member, OrderItem... orderItems) {
        Order order = new Order(LocalDateTime.now(), Status.WAIT, member);
        for (OrderItem orderItem : orderItems) {
            order.getOrderItemList()
                    .add(orderItem);
        }
        return order;
    }

    public Order(LocalDateTime orderDate, Status orderStatus, Member member) {
        this.orderDate   = orderDate;
        this.orderStatus = orderStatus;
        this.member      = member;
    }
}
