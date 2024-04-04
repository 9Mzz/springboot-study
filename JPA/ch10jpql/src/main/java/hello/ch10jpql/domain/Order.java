package hello.ch10jpql.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @Column(name = "orders_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String orderName;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderDate;

    //
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;


    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItems = new ArrayList<>();
    //


    public Order(String orderName) {
        this.orderName = orderName;
    }

    public Order(String orderName, LocalDateTime orderDate) {
        this.orderName = orderName;
        this.orderDate = orderDate;
    }
    //

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                ", orderDate=" + orderDate +
                ", member=" + member +
                '}';
    }
}
