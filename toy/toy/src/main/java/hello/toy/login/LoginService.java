package hello.toy.login;

import hello.toy.member.Member;
import hello.toy.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;


    public Member LoginAct(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .stream()
                .filter(member -> member.getPassword()
                        .equals(password))
                .findFirst()
                .orElse(null);
    }

}
