package hello.test.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item  item;
    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
    // 수량 * 제품 가격
    private int   totalOrderPrice;
    private int   orderCount;

    //
    public OrderItem(Item item, int totalOrderPrice, int orderCount) {
        this.item            = item;
        this.totalOrderPrice = totalOrderPrice;
        this.orderCount      = orderCount;
    }

    //
    public static OrderItem createOrderItem(Item item, int itemPrice, int orderCount) {
        OrderItem orderItem = new OrderItem(item, getTotalOrderPrice(itemPrice, orderCount), orderCount);
        item.removeQuantity(orderCount);
        return orderItem;

    }

    private static int getTotalOrderPrice(int totalOrderPrice, int orderCount) {
        return totalOrderPrice * orderCount;
    }
}
