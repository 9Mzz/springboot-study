package hello.ch04.repsitory;

import hello.ch04.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Member findById(Long id);

    List<Member> findAll();

    void remove(Long id);

    void update(Long id, Member updateParam);

}
