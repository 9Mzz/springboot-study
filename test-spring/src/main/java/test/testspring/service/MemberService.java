package test.testspring.service;

import test.testspring.domain.MemberVo;
import test.testspring.repository.MemberRepository;
import test.testspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository repository = new MemoryMemberRepository();

    /**
     * 회원가입
     *
     * @param memberVo
     * @return
     */
    public Long join(MemberVo memberVo) {

        validateDuplicateMember(memberVo);

        repository.save(memberVo);

        return memberVo.getId();
    }

    private void validateDuplicateMember(MemberVo memberVo) {
        repository.findbyName(memberVo.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다!");
        });
    }

    public List<MemberVo> findAll(MemberVo memberVo) {


        return repository.findAll();
    }

    public Optional<MemberVo> findOne(Long id) {

        return repository.findbyId(id);
    }

}
