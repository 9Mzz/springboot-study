package hello.toyex.login;

import hello.toyex.members.MemberRepository;
import hello.toyex.members.Members;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Slf4j
@Primary
@Repository
@RequiredArgsConstructor
public class LoginRepostiroyImpl implements LoginRepostiroy {

    private final MemberRepository memberRepository;

    @Override
    public Members LoginAct(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .stream()
                .filter(members -> members.getPassword()
                        .equals(password))
                .findFirst()
                .orElse(null);
    }
}
