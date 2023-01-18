package test.testspring.service;

import test.testspring.domain.MemberVo;
import test.testspring.repository.MemberRepository;
import test.testspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemoryService {

    private final MemberRepository repository = new MemoryMemberRepository();

    /**
     * 회원가입 기능
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
            throw new IllegalStateException("중복된 닉네임입니다. 다시 확인해주세요");
        });
    }


    /**
     * 전체 회원 조회
     * @return
     */
    public List<MemberVo> findAll() {

        return repository.findAll();
    }


    public Optional<MemberVo> findOne(Long id) {


        return repository.findbyId(id);
    }

}
