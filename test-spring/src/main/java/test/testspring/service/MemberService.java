package test.testspring.service;

import test.testspring.domain.Member;
import test.testspring.repository.MemberMemberRepository;
import test.testspring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Long join(Member member) {

        //중복회원 검증
        memberDuplicate(member);

        repository.save(member);

        return member.getId();
    }

    private void memberDuplicate(Member member) {
        repository.findbyName(member.getName()).ifPresent(member1 -> {
            throw new IllegalStateException("이미 등록된 회원입니다");
        });
    }

    public List<Member> findAll() {


        return repository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {


        return repository.findbyId(memberId);
    }

}
