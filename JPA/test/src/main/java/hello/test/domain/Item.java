package hello.test.domain;

import hello.test.exception.NoSuchQuantityException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private int    itemPrice;
    private int    itemQuantity;

    public Item(String itemName, int itemPrice, int itemQuantity) {
        this.itemName     = itemName;
        this.itemPrice    = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    public void removeQuantity(int orderCount) {
        int totalQuantity = this.itemQuantity - orderCount;
        if (totalQuantity < 0) {
            throw new NoSuchQuantityException("재고가 부족합니다");
        }
        this.itemQuantity = totalQuantity;
    }
}
