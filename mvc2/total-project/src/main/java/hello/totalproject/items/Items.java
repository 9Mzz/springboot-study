package hello.totalproject.items;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Items {

  private Long    id;
  private String  itemName;
  private Integer price;
  private Integer quantity;


  public Items(String itemName, Integer price, Integer quantity) {
    this.itemName = itemName;
    this.price = price;
    this.quantity = quantity;
  }
}
