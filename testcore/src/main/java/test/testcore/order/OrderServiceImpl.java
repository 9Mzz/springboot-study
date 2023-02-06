package test.testcore.order;

import test.testcore.discount.DiscountPolicy;
import test.testcore.discount.DiscountPolicyImpl;
import test.testcore.member.MemberService;
import test.testcore.member.MemberServiceImpl;
import test.testcore.member.MemberVo;

public class OrderServiceImpl implements OrderService {

    private final DiscountPolicy discountPolicy = new DiscountPolicyImpl();
    private final MemberService  memberService  = new MemberServiceImpl();

    /**
     * 주문 생성
     *
     * @param memberId
     * @param itemName
     * @param itemPrice
     * @return orderVo
     */
    @Override
    public OrderVo createOrder(Long memberId, String itemName, int itemPrice) {

        MemberVo getMember   = memberService.findById(memberId);
        int      discountInt = discountPolicy.discountInt(getMember, itemPrice);

        return new OrderVo(getMember.getId(), itemName, itemPrice, discountInt);
    }


}
