package hello.login.domain.login;

import hello.login.web.member.Member;
import hello.login.web.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

  private final MemberRepository memberRepository;

  public Member loginAct(String loginId, String password) {
    return memberRepository.findByLoginId(loginId)
        .filter(member -> member.getPassword()
            .equals(password))
        .orElse(null);
  }

}
