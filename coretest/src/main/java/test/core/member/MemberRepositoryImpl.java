package test.core.member;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemberRepositoryImpl implements MemberRepository {

    // 실무에서는 동시성 문제 때문에 ConcurrentHashMap을 사용해야 함, 추가로 예외 처리도 해 줘야 함.
    private static Map<Long, MemberVo> store = new HashMap<>();

    @Override
    public void save(MemberVo memberVo) {
        store.put(memberVo.getId(), memberVo);
    }

    @Override
    public MemberVo findById(Long memberId) {
        return store.get(memberId);
    }
}
