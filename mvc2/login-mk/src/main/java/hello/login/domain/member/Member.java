package hello.login.domain.member;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class Member {

  private Long id;

  @NotBlank
  private String loginId;

  @NotBlank
  private String password;

  @NotBlank
  private String name;

  public Member(String loginId, String password, String name) {
    this.loginId = loginId;
    this.password = password;
    this.name = name;
  }
}
