package hello.ch01.repsitory;

import hello.ch01.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findById(Long id);

    List<Member> findAll();

    void updateMember(Long id, Member newMember);

    void deleteMember(Long id);


}
