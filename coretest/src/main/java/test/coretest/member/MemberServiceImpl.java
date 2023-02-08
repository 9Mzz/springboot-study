package test.coretest.member;

public class MemberServiceImpl implements MemberService {

    MemberRepository repository;

    public MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;
    }

    /**
     * 회원 메모리에 저장
     *
     * @param memberVo
     */
    @Override
    public void save(MemberVo memberVo) {

        repository.save(memberVo);
    }

    /**
     * id로 회원 찾기
     *
     * @param memberId
     * @return
     */
    @Override
    public MemberVo findById(Long memberId) {
        return repository.findById(memberId);
    }
}
