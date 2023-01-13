package test.testspring.repository;

import test.testspring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    
    //데이터 저장
    private static Map<Long, Member> store = new HashMap<>();
    //sequence 설정
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {

        member.setId(++sequence);

        store.put(member.getId(), member);


        return member;
    }

    @Override
    public Optional<Member> findbyId(Long id) {


        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findbyName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values());
    }

    public void clearStore(){

        store.clear();

    }

}
