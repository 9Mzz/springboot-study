package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Slf4j
public class MemberRepository {

  private static Map<Long, Member> store = new HashMap<>();
  private static Long sequence = 0L;

  /**
   * 저장
   */
  public Member save(Member member) {
    member.setId(++sequence);
    store.put(member.getId(), member);

    log.info("save end = {}", member);

    return member;
  }

  /**
   * login id 검색
   */
  public Optional<Member> findByLoginId(String loginId) {

    return findAll().stream()
        .filter(member -> member.getLoginId()
            .equals(loginId))
        .findFirst();
  }

  /**
   * 회원번호(Long id)로 인원 검색
   */
  public Member findById(Long findId) {
    return store.get(findId);
  }

  /**
   * 모든 인원 검색
   */
  public List<Member> findAll() {

    return new ArrayList<>(store.values());
  }

  public void clearStore() {
    store.clear();
  }

}
