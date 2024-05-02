package hello.datajpa.repository;

import hello.datajpa.domain.Member;

import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findById(Long id);
    

}
