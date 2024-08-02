package hello.test.service;

import hello.test.domain.dto.MemberDto;
import hello.test.repository.MemberRepository;
import hello.test.repository.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepositoryImpl memberRepositoryImpl;
    private final MemberRepository     memberRepository;

    public MemberDto findMemberDto(Long memberId) {
        return memberRepositoryImpl.getMemberByDto(memberId);
    }

}
