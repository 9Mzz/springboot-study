package test.coretest.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository repository;

    public MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(MemberVo memberVo) {
        repository.save(memberVo);
    }

    @Override
    public MemberVo findById(Long id) {
        return repository.findById(id);
    }
}
