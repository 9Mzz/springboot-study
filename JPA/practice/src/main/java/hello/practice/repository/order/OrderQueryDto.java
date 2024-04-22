package hello.practice.repository.order;

import hello.practice.domain.Delivery;
import hello.practice.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderQueryDto {

    private Long        orderId;
    private String      memberName;  // member.memberName
    private Delivery    delivery;  // delivery
    private Date        orderDate;
    private OrderStatus status;

    private List<OrderItemQueryDto> orderItems;

    public OrderQueryDto(Long orderId, String memberName, Delivery delivery, Date orderDate, OrderStatus status) {
        this.orderId    = orderId;
        this.memberName = memberName;
        this.delivery   = delivery;
        this.orderDate  = orderDate;
        this.status     = status;
    }

    public OrderQueryDto(Long orderId, String memberName, Delivery delivery, Date orderDate, OrderStatus status,
                         List<OrderItemQueryDto> orderItems) {
        this.orderId    = orderId;
        this.memberName = memberName;
        this.delivery   = delivery;
        this.orderDate  = orderDate;
        this.status     = status;
        this.orderItems = orderItems;
    }
}
