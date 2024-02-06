package hello.toyex.members;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Primary
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private static final Map<Long, Members> store    = new ConcurrentHashMap<>();
    private static       Long               sequence = 0L;

    @Override
    public Members save(Members members) {
        members.setId(++sequence);
        store.put(members.getId(), members);
        log.info("save Member = {}", members);
        return members;
    }

    @Override
    public Members findById(Long id) {
        return store.get(id);
    }

    @Override
    public Optional<Members> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(members -> members.getLoginId()
                        .equals(loginId))
                .findFirst();
    }

    @Override
    public List<Members> findAll() {
        return new ArrayList<>(store.values());
    }
}
