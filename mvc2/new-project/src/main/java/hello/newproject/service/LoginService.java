package hello.newproject.service;

import hello.newproject.repository.MemberRepsitory;
import hello.newproject.vo.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

  private final MemberRepsitory memberRepsitory;

  public Member loginAct(String loginId, String password) {
    return memberRepsitory.findByLoginId(loginId)
                          .stream()
                          .filter(member -> member.getPassword()
                                                  .equals(password))
                          .findFirst()
                          .orElse(null);
  }

}
