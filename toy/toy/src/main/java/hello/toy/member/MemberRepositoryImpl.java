package hello.toy.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Primary
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private static final Map<Long, Member> store    = new HashMap<>();
    private static       Long              sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        log.info("saved member = {}", member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return findALl().stream()
                .filter(member -> member.getLoginId()
                        .equals(loginId))
                .findFirst();
    }

    @Override
    public List<Member> findALl() {
        return new ArrayList<>(store.values());
    }
}
