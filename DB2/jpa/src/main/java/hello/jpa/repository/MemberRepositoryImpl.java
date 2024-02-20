package hello.jpa.repository;

import hello.jpa.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final SpringDataJpaItemRepository repository;

    @Override
    public Member save(Member member) {
        Member result = repository.save(member);
        log.info("result = {}", result);
        return result;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Member> findAll() {
        return repository.findAll();
    }


}
