package hello.login.domain.login;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Login {

  @NotEmpty
  private String loginId;
  @NotEmpty
  private String password;


}
