package hello.login.domain.member;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {


  private static Long              sequence = 0L;
  private static Map<Long, Member> store    = new HashMap<>();

}
