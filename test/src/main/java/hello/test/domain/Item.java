package hello.test.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String itemName;
    private int    itemPrice;
    private int    itemQuantity;

    public Item(String itemName, int itemPrice, int itemQuantity) {
        this.itemName     = itemName;
        this.itemPrice    = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    // 구매 시 재고 감소
    public void reduceMoney(int itemQuantityParam) {
        int totalQuantity = this.itemQuantity - itemQuantityParam;
        if (totalQuantity < 0) {
            throw new IllegalStateException("재고 부족");
        }
        this.itemQuantity = totalQuantity;
    }

    // 주문 취소 시 수량 증가
    public void addStock(int countParam) {
        this.itemQuantity += countParam;
    }

}