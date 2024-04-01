package hello.ch07.repository;

import hello.ch07.mapped.Member;

import java.util.List;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long id);

    List<Member> findAll();

    void delete(Long id);

}
