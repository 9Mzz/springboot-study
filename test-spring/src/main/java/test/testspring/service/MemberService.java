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

    /**
     * 회원 가입
     *
     * @param member
     * @return
     */
    public Long join(Member member) {

        //중복 체크
        memberDuplicateCheck(member);
        //저장
        repository.save(member);

        return member.getId();
    }

    /**
     * 회원 중복체크
     *
     * @param member
     */
    private void memberDuplicateCheck(Member member) {

        repository.findbyName(member.getName()).ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }

    /**
     * 모든 회원정보
     *
     * @return
     */
    public List<Member> findAll() {

        return repository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {


        return repository.findbyId(memberId);
    }

}
