package hello.datajpa.repository.member;

import hello.datajpa.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findById(Long id);

    List<Member> findAll();

    void deleteById(Long id);

    void updateMember(Long id,Member updateMember);

}
