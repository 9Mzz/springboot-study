package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    //저장
    Member save(Member member);

    //아이디 검색
    Optional<Member> findById(Long id);

    //이름 검색
    Optional<Member> findByName(String name);

    //모든 회원 검색
    List<Member> findAll();

}
