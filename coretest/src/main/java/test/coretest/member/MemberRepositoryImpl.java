package test.coretest.member;

import java.util.HashMap;
import java.util.Map;

public class MemberRepositoryImpl implements MemberRepository {

    private static Map<Long, MemberVo> store    = new HashMap<>();
    private static Long                sequence = 0L;

    @Override
    public void save(MemberVo memberVo) {

        memberVo.setId(++sequence);
        store.put(memberVo.getId(), memberVo);

    }

    @Override
    public MemberVo findById(Long id) {

        return store.get(id);
    }


}
