package test.testspring.service;

import test.testspring.domain.MemberVo;
import test.testspring.repository.MemberRepository;
import test.testspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemoryService {

    private final MemberRepository repository = new MemoryMemberRepository();

    public Long join(MemberVo memberVo) {

        validateDuplicate(memberVo);

        repository.save(memberVo);

        return memberVo.getId();
    }

    private void validateDuplicate(MemberVo memberVo) {
        repository.findbyName(memberVo.getName())
                  .ifPresent(m -> {
                      throw new IllegalStateException("이미 존재하는 아이디 입니다.");
                  });
    }

    public List<MemberVo> findAll() {

        return repository.findAll();

    }

    public Optional<MemberVo> findOne(Long id) {

        return repository.findbyId(id);
    }

}
