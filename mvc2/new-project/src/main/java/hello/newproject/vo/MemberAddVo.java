package hello.newproject.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberAddVo {

  @NotBlank
  private String loginId;
  @NotBlank
  private String password;
  @NotBlank
  private String name;

  public MemberAddVo() {
  }

  public MemberAddVo(String loginId, String password, String name) {
    this.loginId = loginId;
    this.password = password;
    this.name = name;
  }
}
