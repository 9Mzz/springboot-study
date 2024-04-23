package hello.practice.domain.order;

import hello.practice.domain.Address;
import hello.practice.domain.OrderStatus;
import lombok.Getter;

import java.util.Date;

@Getter
public class OrderSimpleQueryDto {

    private Long        id;
    private String      memberName;
    private Date        orderDate;
    private OrderStatus status;
    private Address     address;

    public OrderSimpleQueryDto(Long id, String memberName, Date orderDate, OrderStatus status, Address address) {
        this.id         = id;
        this.memberName = memberName;
        this.orderDate  = orderDate;
        this.status     = status;
        this.address    = address;
    }
}
