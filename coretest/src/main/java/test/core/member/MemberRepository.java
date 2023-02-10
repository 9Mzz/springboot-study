package test.core.member;

public interface MemberRepository {

    void save(MemberVo memberVo);

    MemberVo findById(Long memberId);

}
