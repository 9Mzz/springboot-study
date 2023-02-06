package test.testcore.member;

import java.util.HashMap;
import java.util.Map;

public class MemberRepositoryImpl implements MemberRepository {

    private static Map<Long, MemberVo> store = new HashMap<>();

    @Override
    public void join(MemberVo memberVo) {

        store.put(memberVo.getId(), memberVo);

    }

    @Override
    public MemberVo findById(Long id) {

        return store.get(id);
    }
}
