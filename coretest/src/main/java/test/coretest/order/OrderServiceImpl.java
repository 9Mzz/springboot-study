package test.coretest.order;

import test.coretest.discount.DiscountPolicy;
import test.coretest.member.MemberService;
import test.coretest.member.MemberVo;

public class OrderServiceImpl implements OrderService {

    MemberService  memberService;
    DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberService memberService, DiscountPolicy discountPolicy) {
        this.memberService  = memberService;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public OrderVo createOrder(Long memberId, String itemName, int itemPrice) {

        MemberVo getMember = memberService.findById(memberId);

        int discount = discountPolicy.discount(getMember, itemPrice);

        return new OrderVo(getMember.getMemberId(), itemName, itemPrice, discount);
    }


}
