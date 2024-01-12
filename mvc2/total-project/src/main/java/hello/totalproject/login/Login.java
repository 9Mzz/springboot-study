package hello.totalproject.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Login {

  @NotBlank
  private String loginId;
  @NotBlank
  private String password;
}
