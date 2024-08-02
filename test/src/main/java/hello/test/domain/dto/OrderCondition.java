package hello.test.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderCondition {

    private Long itemId;
    private int  orderCount;

    public OrderCondition(Long itemId, int orderCount) {
        this.itemId     = itemId;
        this.orderCount = orderCount;
    }
}
