package hello.login.domain.login;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
public class Login {

  @NotBlank
  private String loginId;
  @NotBlank
  private String password;

}
