package test.core.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import test.core.discount.DiscountPolicy;
import test.core.member.MemberRepository;
import test.core.member.MemberVo;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy   discountPolicy;


    // 의존성 주입
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,@Qualifier("fixDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy   = discountPolicy;
    }

    @Override
    public OrderVo createOrder(Long memberId, String itemName, int itemPrice) {
        MemberVo member   = memberRepository.findById(memberId);
        int      discount = discountPolicy.discount(member, itemPrice);

        return new OrderVo(member.getId(), itemName, itemPrice, discount);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
