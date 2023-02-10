package test.core.member;

public interface MemberService {

    void join(MemberVo memberVo);

    MemberVo findMember(Long memberId);

}

