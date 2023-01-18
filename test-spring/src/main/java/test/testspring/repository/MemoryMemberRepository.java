package test.testspring.repository;

import test.testspring.domain.MemberVo;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, MemberVo> store = new HashMap<>();

    private static Long sequence = 0L;

    public void clearStore(){
        store.clear();
    }

    @Override
    public MemberVo save(MemberVo memberVo) {

        //memberVo의 id를 1씩 증가하는 sequence로 설정
        memberVo.setId(++sequence);

        //정보를 저장하는 map에다 데이터를 저장
        store.put(memberVo.getId(), memberVo);

        // memberVo를 저장
        return memberVo;
    }

    @Override
    public Optional<MemberVo> findbyId(Long id) {

        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<MemberVo> findbyName(String name) {

        return store.values().stream()
                .filter(memberVo -> memberVo.getName().equals(name))
                .findAny();
    }

    @Override
    public List<MemberVo> findAll() {

        return new ArrayList<>(store.values());
    }
}
