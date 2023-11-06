package hello.login.domain.member;

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


  private static Long              sequence = 0L;
  private static Map<Long, Member> store    = new HashMap<>();


  public Member save(Member member) {
    member.setId(++sequence);
    store.put(member.getId(), member);

    log.info("save Data = {}", member);
    return member;
  }

  public Member findById(Long id) {
    return store.get(id);
  }

  public List<Member> findAll() {
    return new ArrayList<>(store.values());
  }

  public Optional<Member> findByLoginId(String loginId) {

    return findAll().stream().filter(member -> member.getLoginId().equals(loginId)).findFirst();
  }


}
