package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

  private final MemberRepository memberRepository;

  public Member loginCheck(String loginId, String password) {

/*    Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);
    Member           member             = findMemberOptional.get();
    if (member.getPassword().equals(password)) {
      return member;
    } else {
      return null;
    }*/

    return memberRepository.findByLoginId(loginId)
        .filter(member -> member.getPassword().equals(password))
        .orElse(null);
  }


}
