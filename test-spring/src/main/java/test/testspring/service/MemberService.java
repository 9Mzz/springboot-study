package test.testspring.service;

import test.testspring.domain.Member;
import test.testspring.repository.MemberMemberRepository;
import test.testspring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository repository = new MemberMemberRepository();

    public Long join(Member member) {

        memberNameDuplicate(member);

        repository.save(member);

        return member.getId();
    }

    private void memberNameDuplicate(Member member) {
        repository.findbyName(member.getName())
                  .ifPresent(member1 -> {
                      throw new IllegalStateException("이미 중복된 아이디 입니다");
                  });
    }

    public List<Member> findAll() {


        return repository.findAll();
    }

    public Optional<Member> findOne(Long memberid) {

        return repository.findbyId(memberid);
    }

}
