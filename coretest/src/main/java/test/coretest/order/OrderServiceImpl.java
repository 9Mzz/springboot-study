package test.coretest.order;

import test.coretest.discount.DiscountPolicy;
import test.coretest.member.MemberService;
import test.coretest.member.MemberVo;

public class OrderServiceImpl implements OrderService {

    private final DiscountPolicy discountPolicy;
    private final MemberService  memberService;

    public OrderServiceImpl(DiscountPolicy discountPolicy, MemberService memberService) {
        this.discountPolicy = discountPolicy;
        this.memberService  = memberService;
    }

    /**
     * 주문내역 생성
     *
     * @param memberId
     * @param itemName
     * @param itemPrice
     * @return
     */
    @Override
    public OrderVo createOrder(Long memberId, String itemName, int itemPrice) {

        MemberVo member   = memberService.findById(memberId);
        int      discount = discountPolicy.discount(member, itemPrice);

        return new OrderVo(member.getId(), itemName, itemPrice, discount);
    }
}
