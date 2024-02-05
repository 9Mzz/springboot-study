package hello.servlet.domain.member;

import org.apache.catalina.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository2 {

    private static Map<Long, Member2> store    = new HashMap<>();
    private static Long               sequence = 0L;

    private static final MemberRepository2 instance = new MemberRepository2();

    public static MemberRepository2 getInstance() {

        return instance;
    }

    private MemberRepository2() {

    }

    /**
     * 회원 저장
     *
     * @param member2
     * @return
     */
    public Member2 save(Member2 member2) {

        member2.setId(++sequence);
        store.put(member2.getId(), member2);

        return member2;
    }

    /**
     * ID로 회원 검색
     *
     * @param id
     * @return
     */
    public Member2 findById(Long id) {
        return store.get(id);
    }

    /**
     * 모든 회원 검색
     *
     * @return
     */
    public List<Member2> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * Map 청소
     */
    public void clearStore() {
        store.clear();
    }

}
