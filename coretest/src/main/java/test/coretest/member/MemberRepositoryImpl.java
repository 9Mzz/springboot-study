package test.coretest.member;

import java.util.HashMap;
import java.util.Map;

public class MemberRepositoryImpl implements MemberRepository {

    private static Map<Long, MemberVo> store = new HashMap<>();


    @Override
    public void save(MemberVo memberVo) {

        store.put(memberVo.getMemberId(), memberVo);

    }

    @Override
    public MemberVo findById(Long memberId) {


        return store.get(memberId);
    }
}
