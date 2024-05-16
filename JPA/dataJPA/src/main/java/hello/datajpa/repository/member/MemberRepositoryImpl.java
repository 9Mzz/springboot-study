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
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberDataJPA repository;

    @Override
    public Member save(Member member) {
        repository.save(member);
        log.info("save member : {}", member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Member> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void updateMember(Long id, Member updateMember) {
        Member newMember = findById(id).get();
        newMember.setName(updateMember.getName());
        newMember.setAge(updateMember.getAge());
    }

}
