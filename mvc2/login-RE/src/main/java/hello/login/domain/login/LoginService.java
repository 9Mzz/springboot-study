package hello.login.domain.login;

import hello.login.web.member.Member;
import hello.login.web.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

  private final MemberRepository memberRepository;


  public Member loginAction(String loginId, String password) {

    return memberRepository.findByLoginId(loginId)
        .filter(member -> member.getPassword()
            .equals(password))
        .orElse(null);
  }

}
