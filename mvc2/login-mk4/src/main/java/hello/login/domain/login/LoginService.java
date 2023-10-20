package hello.login.domain.login;

import hello.login.web.member.Member;
import hello.login.web.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

  private final MemberRepository memberRepository;

  public Member loginAction(String loginId, String password) {

    return memberRepository.findByLoginId(loginId)
        .stream()
        .filter(member -> member.getPassword()
            .equals(password))
        .findFirst()
        .orElse(null);
  }


}
