package hello.totalproject.items;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ItemsAddForm {

  @NotBlank
  private String  itemName;
  @Range(min = 1000, max = 1000000)
  @NotNull
  private Integer price;
  @Max(9999)
  @NotNull
  private Integer quantity;


}
