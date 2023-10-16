package hello.login.domain.member;


import hello.login.web.member.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class MemberRepository {

  private static final Map<Long, Member> store    = new HashMap<>();
  private static       Long              sequence = 0L;

  public Member save(Member member) {
    member.setId(++sequence);
    store.put(member.getId(), member);

    log.info("saved data = {}", member);
    return member;
  }

  public Member findById(Long memberId) {
    return store.get(memberId);
  }

  public Optional<Member> findByLoginId(String loginId) {
    return
        findAll().stream()
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