package hello.login.web.login;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Login {

  @NotBlank
  private String loginId;
  @NotBlank
  private String password;

}
