package hello.totalproject.login;

import hello.totalproject.member.Member;
import hello.totalproject.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

  private final MemberRepository memberRepository;

  public Member LoginAction(String loginId, String password) {
    return memberRepository.findByLoginId(loginId)
                           .filter(member -> member.getPassword()
                                                   .equals(password))
                           .orElse(null);

  }

}
