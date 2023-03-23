package hello.itemservice.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data //는 위험하다
//@Setter
//@ToString
public class Item {

    // int 대신 Integer 쓴 이유 : 값이 없을 수도 있어서
    private Long    id;
    private String  itemName;
    private Integer price;
    private Integer quanity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quanity) {
        this.itemName = itemName;
        this.price = price;
        this.quanity = quanity;
    }
}
