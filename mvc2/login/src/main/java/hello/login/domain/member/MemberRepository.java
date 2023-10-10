package hello.login.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
@Slf4j
@Repository
public class MemberRepository {

  private static Map<Long, Member> store    = new HashMap<>();
  private static Long              sequence = 0L;

  /**
   * 회원 저장
   *
   * @param member
   * @return
   */
  public Member save(Member member) {
    member.setId(++sequence);
    store.put(member.getId(), member);

    log.info("Member save = {}", member);

    return member;
  }

  /**
   * 회원번호(loginId)로 찾기
   *
   * @param loginId
   * @return
   */
  public Member findById(Long loginId) {

    Member member = store.get(loginId);
    log.info("findById = {}", member);
    return member;
  }

  /**
   * 모든 회원 가져오기
   *
   * @return 회원 리스트
   */
  public List<Member> findAll() {

    ArrayList<Member> arrayList = new ArrayList<>(store.values());
    log.info("findAll list = {}", arrayList);
    return arrayList;
  }

  /**
   * loginId로 회원 검색
   *
   * @param loginId
   * @return
   */
  public Optional<Member> findByLoginId(String loginId) {
    Optional<Member> findResult = findAll().stream()
        .filter(member -> member.getLoginId()
            .equals(loginId))
        .findFirst();

    log.info("findByLoginId = {}", findResult);
    return findResult;
  }

  public void clearStore() {
    store.clear();
  }
}
