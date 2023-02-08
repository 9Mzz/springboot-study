package test.coretest.member;

public interface MemberRepository {

    void save(MemberVo memberVo);

    MemberVo findById(Long memberId);

}
