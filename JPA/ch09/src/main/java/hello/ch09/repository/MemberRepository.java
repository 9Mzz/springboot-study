package hello.ch09.repository;

import hello.ch09.domain.Member;

import java.util.List;

public interface MemberRepository {

    Member save(Member member);

    Member findById(Long id);

    List<Member> findAll();

}
