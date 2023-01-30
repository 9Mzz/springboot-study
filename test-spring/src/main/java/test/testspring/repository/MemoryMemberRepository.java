package test.testspring.repository;

import test.testspring.domain.MemberVo;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, MemberVo> store = new HashMap<>();

    private static Long sequence = 0L;

    public void clearStore() {
        store.clear();
    }

    @Override
    public MemberVo save(MemberVo memberVo) {
        memberVo.setId(++sequence);
        store.put(memberVo.getId(), memberVo);
        return memberVo;
    }

    @Override
    public Optional<MemberVo> findbyId(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<MemberVo> findbyName(String name) {
        return store.values().stream().filter(memberVo -> memberVo.getName().equals(name)).findAny();
    }

    @Override
    public List<MemberVo> findAll() {
        return new ArrayList<>(store.values());
    }
}
