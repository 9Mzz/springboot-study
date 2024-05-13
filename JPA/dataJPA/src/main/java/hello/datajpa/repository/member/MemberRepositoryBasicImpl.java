package hello.datajpa.repository.member;

import hello.datajpa.domain.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class MemberRepositoryBasicImpl implements MemberRepository {

    private final DataMemberRepository repository;

    @Override
    public Long save(Member member) {
        repository.save(member);
        return member.getId();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Member> findAll() {
        List<Member> all = repository.findAll();
        return all;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
