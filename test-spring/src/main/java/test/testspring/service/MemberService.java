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
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member) {

        memberDuplicate(member);

        repository.save(member);

        return member.getId();
    }

    /**
     * 회원가입 중복체크
     * @param member
     */
    private void memberDuplicate(Member member) {
        repository.findbyName(member.getName()).ifPresent(memberVo1 -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }

    /**
     * 모든 회원 조회
     * @return
     */
    public List<Member> findAll() {

        return repository.findAll();

    }

    public Optional<Member> findAny(Long id) {


        return repository.findbyId(id);
    }

}
