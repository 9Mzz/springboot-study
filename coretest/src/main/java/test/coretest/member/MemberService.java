package test.coretest.member;

public interface MemberService {


    /**
     * 회원 메모리에 저장
     * @param memberVo
     */
    void save(MemberVo memberVo);

    /**
     * id로 회원 찾기
     * @param memberId
     * @return
     */
    MemberVo findById(Long memberId);

}
