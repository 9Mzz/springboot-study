package hello.newproject.vo;

import lombok.Data;

@Data
public class Member {

  private Long   id;
  private String loginId;
  private String password;
  private String name;

  public Member() {
  }

  public Member(String loginId, String password, String name) {
    this.loginId = loginId;
    this.password = password;
    this.name = name;
  }
}
