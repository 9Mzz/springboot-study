package com.example.demo.domain;

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
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item  item;
    private int   totalOrderPrice;
    private int   count;

    public OrderItem(Item item, int totalOrderPrice, int count) {
        this.item            = item;
        this.totalOrderPrice = totalOrderPrice;
        this.count           = count;
    }

    //
    public static OrderItem createOrderItem(Item item, int itemPrice, int count) {
        OrderItem orderItem = new OrderItem(item, getTotalOrderPrice(itemPrice, count), count);
        item.reduceCount(count);
        return orderItem;
    }

    private static int getTotalOrderPrice(int itemPrice, int count) {
        return itemPrice * count;
    }
}
