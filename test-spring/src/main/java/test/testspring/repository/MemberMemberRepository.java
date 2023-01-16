package test.testspring.repository;

import test.testspring.domain.Member;

import java.util.*;

public class MemberMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();

    private static Long sequence = 0L;


    public void clearStore(){


        store.clear();
    }

    @Override
    public Member save(Member member) {

        //id를 sequence로 설정
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


        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values());
    }
}
