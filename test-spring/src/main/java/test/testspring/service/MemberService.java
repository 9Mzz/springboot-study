package test.testspring.service;

import org.springframework.transaction.annotation.Transactional;
import test.testspring.domain.Member;
import test.testspring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;


@Transactional
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Long join(Member member) {

        memberDuplicateCheck(member);

        repository.save(member);

        return member.getId();
    }

    private void memberDuplicateCheck(Member member) {
        repository.findByName(member.getName()).ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }

    public List<Member> findAll() {


        return repository.findAll();

    }

    public Optional<Member> findOne(Long memberId) {

        return repository.findById(memberId);
    }

}
