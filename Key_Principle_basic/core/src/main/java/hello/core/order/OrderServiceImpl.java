package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderSerivce {

    private final MemberRepository repository     = new MemoryMemberRepository();
    //고정 할인
    //    private final DiscountPolicy   discountPolicy = new FixDiscountPolicy();
    // % 할인
    private final DiscountPolicy   discountPolicy = new RateDiscountPolicy();


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member        = repository.findById(memberId);
        int    discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
