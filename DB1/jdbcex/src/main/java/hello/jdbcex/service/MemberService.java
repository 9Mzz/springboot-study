package hello.jdbcex.service;

import hello.jdbcex.domain.Member;
import hello.jdbcex.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void moneyTransfer(String toId, String fromId, int money) {
        Member toMember   = memberRepository.findById(toId);
        Member fromMember = memberRepository.findById(fromId);

        memberRepository.update(toMember.getMemberId(), toMember.getMoney() + money);
        validation(fromId);
        memberRepository.update(fromMember.getMemberId(), fromMember.getMoney() - money);
    }

    private static void validation(String fromId) {
        if(fromId.equals("ex")) {
            throw new IllegalStateException("송금 중 오류 발생");
        }
    }

}
