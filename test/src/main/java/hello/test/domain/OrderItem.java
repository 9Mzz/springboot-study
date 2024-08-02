package hello.test.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item  item;

    private int totalOrderPrice;        // 총 가격
    private int orderCount; // 주문 수량

    // OrderItem 생성
    public static OrderItem createOrderItem(Item item, int orderCount) {
        return new OrderItem(item, getTotalOrderPrice(item, orderCount), orderCount);
    }

    // 주문 취소
    public void cancel() {
        getItem().addStock(orderCount);
    }

    private static int getTotalOrderPrice(Item item, int orderCount) {
        return item.getItemPrice() * orderCount;
    }

    //
    public OrderItem(Item item, int totalOrderPrice, int orderCount) {
        this.item            = item;
        this.totalOrderPrice = totalOrderPrice;
        this.orderCount      = orderCount;
    }

    //
    public OrderItem(Order order, Item item, int totalOrderPrice, int orderCount) {
        this.order           = order;
        this.item            = item;
        this.totalOrderPrice = totalOrderPrice;
        this.orderCount      = orderCount;
    }
}