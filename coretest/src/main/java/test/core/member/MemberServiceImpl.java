package test.core.member;

public class MemberServiceImpl implements MemberService {

    //      인터페이스에 의존하는 memberRepository(추상화된) = 구체화된 호출
    // 추상화 구체화 둘다 사용중이므로 좋은 코드가 아니다.
    // 변경 시 문제가 된다. DIP를 위반하고 있는 코드이다.
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(MemberVo memberVo) {
        memberRepository.save(memberVo);
    }

    @Override
    public MemberVo findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
