package test.testspring.repository;

import org.springframework.beans.factory.config.RuntimeBeanReference;
import test.testspring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findbyId(Long id);

    Optional<Member> findbyName(String name);

    List<Member> findAll();
}


