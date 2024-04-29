package hello.practice.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemQueryDto {

    // @JsonIgnore
    private Long   orderId;
    private String memberName;
    private int    orderPrice;
    private int    count;

    public OrderItemQueryDto(Long orderId, String memberName, int orderPrice, int count) {
        this.orderId    = orderId;
        this.memberName = memberName;
        this.orderPrice = orderPrice;
        this.count      = count;
    }
}
