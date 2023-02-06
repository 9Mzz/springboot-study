package test.testcore.member;

public class MemberServiceImpl implements MemberService {

    MemberRepository repository = new MemberRepositoryImpl();

    @Override
    public void join(MemberVo memberVo) {

        repository.join(memberVo);
    }

    @Override
    public MemberVo findById(Long id) {


        return repository.findById(id);
    }
}
