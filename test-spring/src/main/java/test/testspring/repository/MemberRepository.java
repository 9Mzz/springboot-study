package test.testspring.repository;

import test.testspring.domain.MemberVo;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    MemberVo save(MemberVo memberVo);

    Optional<MemberVo> findbyId(Long id);

    Optional<MemberVo> findbyName(String name);

    List<MemberVo> findAll();

}
