package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Mmap;
import org.springframework.stereotype.Repository;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static final Map<Long, Member> store    = new HashMap<>();    //static 사용
    private static       Long              sequence = 0L;   //static 사용

    public Member save(Member member) {

        member.setId(++sequence);
        log.info("save data : {}", member);
        store.put(member.getId(), member);
        return member;
    }


    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
/*

        List<Member> all = findAll();
        for (Member m : all) {
            if(m.getLoginId()
                .equals(loginId)) {
                return Optional.of(m);
            }

        }
        return Optional.empty();
*/
        return findAll().stream()
                        .filter(member -> member.getLoginId()
                                                .equals(loginId))
                        .findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();

    }

}
