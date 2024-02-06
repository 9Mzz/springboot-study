package hello.toyex.items;

import lombok.Data;

@Data
public class Items {

    private Long    id;
    private String  itemName;
    private Integer price;
    private Integer quantity;

    //
    public Items() {
    }

    public Items(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public Items(Long id, String itemName, Integer price, Integer quantity) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
