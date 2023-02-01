package test.testspring.repository;

import test.testspring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store    = new HashMap<>();
    private static Long              sequence = 0L;

    /**
     * 회원 저장
     * @param member
     * @return
     */
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    /**
     * ID로 회원 검색
     * @param id
     * @return
     */
    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id));
    }

    /**
     * 이름(name)으로 회원 검색
     * @param name
     * @return
     */
    @Override
    public Optional<Member> findByName(String name) {
        return store.values()
                    .stream().filter(member -> member.getName().equals(name))
                    .findAny();
    }

    /**
     * 모든 회원 리스트
     * @return
     */
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * store Map 비우기
     */
    public void storeClear() {
        store.clear();
    }


}
