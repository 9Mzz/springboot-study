package test.testcore.member;

public interface MemberRepository {

    void join(MemberVo memberVo);

    MemberVo findById(Long id);


}
