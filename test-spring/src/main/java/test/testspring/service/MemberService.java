package test.testspring.service;

import org.springframework.transaction.annotation.Transactional;
import test.testspring.domain.Member;
import test.testspring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Long join(Member member) {

        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });


        memberRepository.save(member);
        return member.getId();
    }

    public Optional<Member> getOne(Long memberId) {

        return memberRepository.findById(memberId);
    }

    public List<Member> getAll() {

        return memberRepository.findAll();
    }


}
