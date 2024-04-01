package hello.ch05.repository;

import hello.ch05.domain.Member;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface MemberRepository {

    Member save(Member member);

    Member findById(Long id);

    List<Member> findAll();

    void memberRemove(Long id);

}
