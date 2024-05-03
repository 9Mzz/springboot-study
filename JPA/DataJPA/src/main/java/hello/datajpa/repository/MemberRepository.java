package hello.datajpa.repository;

import hello.datajpa.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    void update(Long id, Member memberParam);

    Optional<Member> findById(Long id);

    List<Member> findAll();


}
