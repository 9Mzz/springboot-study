package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store    = new HashMap<>();
    private static long              sequence = 0L;


    @Override
    public Member save(Member member) {

        //id 설정(seq로)
        member.setId(++sequence);
        store.put(member.getId(), member);

        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {


        return store.values().stream().filter(member -> member.getName().equals(name))
                    .findAny();
    }

    @Override
    public List<Member> findAll() {

        //store 에 있는 Member를 갖고 (key(Long0 - values(Member) 쌍에서 values를 모아서) 새로운 ArrayList를 만들어 반환.
        return new ArrayList<>(store.values());
    }

    public void clearStore() {

        store.clear();

    }
}
