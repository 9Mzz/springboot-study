package test.testspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.testspring.domain.MemberVo;
import test.testspring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(MemberVo memberVo) {

        memberRepository.findbyName(memberVo.getName()).ifPresent(memberVo1 -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });

        memberRepository.save(memberVo);

        return memberVo.getId();
    }


    public Optional<MemberVo> findOnd(Long id) {

        return memberRepository.findbyId(id);
    }

    public List<MemberVo> findAll() {

        return memberRepository.findAll();
    }


}
