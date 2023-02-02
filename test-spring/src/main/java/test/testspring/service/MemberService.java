package test.testspring.service;

import org.springframework.transaction.annotation.Transactional;
import test.testspring.domain.Member;
import test.testspring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Long join(Member member) {
        duplicateCheck(member);

        repository.save(member);

        return member.getId();
    }

    private void duplicateCheck(Member member) {
        repository.findByName(member.getName())
                  .ifPresent(m -> {
                      throw new IllegalStateException("이미 존재하는 회원입니다");
                  });
    }

    public List<Member> findAll() {


        return repository.findAll();

    }

    public Optional<Member> findOne(Long id) {

        return repository.findById(id);
    }

}
