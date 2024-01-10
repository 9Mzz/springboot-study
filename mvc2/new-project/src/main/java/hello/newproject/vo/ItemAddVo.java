package hello.newproject.vo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ItemAddVo {


  @NotEmpty
  private String itemName;

  @Range(min = 1000, max = 1000000)
  @NotNull
  private Integer price;

  @Max(9999)
  @NotNull
  private Integer quantity;

}
