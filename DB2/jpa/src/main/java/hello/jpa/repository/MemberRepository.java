package hello.jpa.repository;

import hello.jpa.domain.Member;

import java.io.LineNumberInputStream;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);


    Optional<Member> findById(Long id);

    List<Member> findAll();


}
