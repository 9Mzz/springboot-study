package hello.login.web.member;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Member {

  private Long   id;
  @NotBlank
  private String loginId;
  @NotBlank
  private String password;
  @NotBlank
  private String name;


}
