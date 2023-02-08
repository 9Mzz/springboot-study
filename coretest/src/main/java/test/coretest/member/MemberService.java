package test.coretest.member;

public interface MemberService {

    void save(MemberVo memberVo);

    MemberVo findById(Long id);

}
