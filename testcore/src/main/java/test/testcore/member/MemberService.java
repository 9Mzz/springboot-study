package test.testcore.member;

public interface MemberService {

    void join(MemberVo memberVo);

    MemberVo findById(Long id);

}
