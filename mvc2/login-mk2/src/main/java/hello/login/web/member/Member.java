package hello.login.web.member;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Member {

  private Long   id;
  @NotEmpty
  private String loginId;
  @NotEmpty
  private String password;
  @NotEmpty
  private String name;

}
