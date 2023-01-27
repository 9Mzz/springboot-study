package test.testspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.testspring.domain.MemberVo;
import test.testspring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository repository;

    @Autowired
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Long join(MemberVo memberVo) {

        memberDuplicateCheck(memberVo);

        repository.save(memberVo);

        return memberVo.getId();
    }

    private void memberDuplicateCheck(MemberVo memberVo) {
        repository.findbyName(memberVo.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    public List<MemberVo> findAll() {

        return repository.findAll();
    }

    public Optional<MemberVo> findOne(Long memberId) {


        return repository.findbyId(memberId);
    }
}
