package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository repository     = new MemoryMemberRepository();
    private final DiscountPolicy   discountPolicy = new FixDiscountPolicy();

    /**
     * 주문 생성
     *
     * @param memberId
     * @param itemName
     * @param itemPrice
     * @return Order
     */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member        = repository.findById(memberId);
        int    discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


}
