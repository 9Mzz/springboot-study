package hello.totalproject.member;

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


  private static final Map<Long, Member> store   = new HashMap<>();
  private static       Long              sequcne = 0L;

  public Member save(Member member) {
    member.setId(++sequcne);
    store.put(member.getId(), member);
    log.info(" saved member = {}", member);
    return member;
  }

  public Member findById(Long id) {
    return store.get(id);
  }

  public List<Member> findAll() {
    return new ArrayList<>(store.values());
  }

  public Optional<Member> findByLoginId(String loginId) {
    return findAll().stream()
                    .filter(member -> member.getLoginId()
                                            .equals(loginId))
                    .findFirst();
  }

}
