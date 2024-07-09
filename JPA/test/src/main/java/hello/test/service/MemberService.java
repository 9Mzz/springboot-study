package hello.test.service;

import hello.test.domain.Member;
import hello.test.repository.member.MemberRepository;
import hello.test.repository.member.MemberRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long memberSave(Member member) {
        return memberRepository.save(member)
                .getId();
    }

}
