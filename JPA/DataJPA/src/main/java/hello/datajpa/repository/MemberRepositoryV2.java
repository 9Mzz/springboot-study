package hello.datajpa.repository;

import hello.datajpa.domain.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
@RequiredArgsConstructor
public class MemberRepositoryV2 implements MemberRepository {

    private final SpringDataJpaMemberRepository repository;

    @Override
    public Member save(Member member) {
        return repository.save(member);
    }

    @Override
    public void update(Long id, Member memberParam) {
        Member findMember = repository.findById(id)
                .orElseThrow();
        findMember.setName(memberParam.getName());
        findMember.setAge(memberParam.getAge());
    }


    @Override
    public Optional<Member> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Member> findAll() {
        return List.of();
    }
}
