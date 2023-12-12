package hello.login.web.member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotBlank.List;
import lombok.Data;

@Data
public class Member {

  private long   id;
  @NotBlank
  private String loginId;
  @NotBlank
  private String password;
  @NotBlank
  private String name;

}
