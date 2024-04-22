package hello.practice.repository.order;

import hello.practice.domain.Address;
import hello.practice.domain.OrderStatus;
import lombok.Getter;

import java.util.Date;

@Getter
public class OrderFlatDto {

    private Long        orderId;
    private String      memberName;
    private Date        orderDate;
    private Address     address;
    private OrderStatus status;
    private String      itemName;
    private int         orderPrice;
    private int         count;

    public OrderFlatDto(Long orderId, String memberName, Date orderDate, Address address, OrderStatus status,
                        String itemName, int orderPrice, int count) {
        this.orderId    = orderId;
        this.memberName = memberName;
        this.orderDate  = orderDate;
        this.address    = address;
        this.status     = status;
        this.itemName   = itemName;
        this.orderPrice = orderPrice;
        this.count      = count;
    }
}
