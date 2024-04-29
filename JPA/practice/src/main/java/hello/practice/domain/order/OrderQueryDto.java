package hello.practice.domain.order;


import hello.practice.domain.Address;
import hello.practice.domain.OrderStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
// @EqualsAndHashCode(of = "orderId")
public class OrderQueryDto {

    private Long                    orderId;
    private String                  memberName;
    private Date                    orderDate;
    private OrderStatus             status;
    private Address                 address;
    private List<OrderItemQueryDto> orderitems;

    public OrderQueryDto(Long orderId, String memberName, Date orderDate, OrderStatus status, Address address) {
        this.orderId    = orderId;
        this.memberName = memberName;
        this.orderDate  = orderDate;
        this.status     = status;
        this.address    = address;
    }
}
