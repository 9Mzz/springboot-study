package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.security.auth.login.LoginContext;
import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store    = new HashMap<>();   //static 사용
    private static Long              sequence = 0L;

    /**
     * 회원가입
     */
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        log.info("save : {}", member);
        return member;

    }


    public Optional<Member> find(Long findId) {
        return Optional.ofNullable(store.get(findId));
    }

    public Optional<Member> findByLoginId(String loginId) {

        return findAll().stream()
                        .filter(member -> member.getLoginId()
                                                .equals(loginId))
                        .findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
