package hello.practice.repository;

import hello.practice.domain.Address;
import hello.practice.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OrderSimpleQueryDto {
    private Long          orderId;
    private String        name;
    private LocalDateTime orderDate;
    private OrderStatus   orderStatus;
    private Address       address;

    public OrderSimpleQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus,
                               Address address) {
        this.orderId     = orderId;
        this.name        = name;
        this.orderDate   = orderDate;
        this.orderStatus = orderStatus;
        this.address     = address;
    }


}