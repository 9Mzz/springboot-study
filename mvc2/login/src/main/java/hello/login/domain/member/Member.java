package hello.login.domain.member;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Member {


  private Long   id;
  @NotBlank
  private String loginId; //로그인 Id

  @NotBlank
  private String name;  //사용자 이름

  @NotBlank
  private String password;  // 비밀번호

  public Member(String loginId, String name, String password) {
    this.loginId = loginId;
    this.name = name;
    this.password = password;
  }
}
