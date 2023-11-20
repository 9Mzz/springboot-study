package hello.login.domain.login;

import hello.login.web.member.Member;
import hello.login.web.member.MemberRepository;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Mmap;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
