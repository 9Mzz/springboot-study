package test.testspring.repository;

import test.testspring.domain.MemberVo;

import java.lang.reflect.Member;
import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, MemberVo> store = new HashMap<>();

    private static Long sequence = 0L;

    public void clearStore() {
        store.clear();
    }


    @Override
    public MemberVo save(MemberVo member) {
        System.out.println("MemoryMemberRepository.save");

        member.setId(++sequence);
        store.put(member.getId(), member);


        return member;
    }

    @Override
    public Optional<MemberVo> findbyId(Long id) {
        System.out.println("MemoryMemberRepository.findbyId");

        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<MemberVo> findbyName(String name) {
        System.out.println("MemoryMemberRepository.findbyName");

        return store.values().stream().filter(memberVo -> memberVo.getName().equals(name)).findAny();
    }

    @Override
    public List<MemberVo> findAll() {
        System.out.println("MemoryMemberRepository.findAll");

        return new ArrayList<>(store.values());
    }
}
