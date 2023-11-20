package hello.login.web.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class MemberRepository {

  private static final Map<Long, Member> store    = new HashMap<>();
  private static       Long              sequence = 0L;

  public Member save(Member member) {
    member.setId(++sequence);
    store.put(member.getId(), member);

    log.info("save data = {}", member);
    return member;
  }

  public Member findbyId(Long id) {
    return store.get(id);
  }

  public List<Member> findAll() {

    ArrayList<Member> members = new ArrayList<>(store.values());
    log.info("List data = {}", members);
    return members;
  }

  public Optional<Member> findByLoginId(String loginId) {
    return findAll().stream()
        .filter(member -> member.getLoginId()
            .equals(loginId))
        .findFirst();
  }

}
