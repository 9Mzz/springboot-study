package hello.test.domain;


import hello.test.exception.NoSuchQuantityException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String itemName;
    private int    price;
    private int    stockQuantity;

    //
    public Item(String itemName, int price, int stockQuantity) {
        this.itemName      = itemName;
        this.price         = price;
        this.stockQuantity = stockQuantity;
    }

    /**
     * 수량 감소
     */
    public void removeQuantity(int stockQuantity) {
        int totalQuantity = this.stockQuantity - stockQuantity;
        if (totalQuantity < 0) {
            throw new NoSuchQuantityException("수량이 부족합니다");
        }
        this.stockQuantity = totalQuantity;
    }
}
