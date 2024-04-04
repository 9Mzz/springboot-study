package hello.ch10jpql.repository;

import hello.ch10jpql.domain.Member;

import java.util.List;

public interface MemberRepository {

    Member save(Member member);

    Member findById(Long id);

    List<Member> findAll();

}
