package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFicAmount = 1000;   //1000원 할인

    /**
     * @param member
     * @param price
     * @return 할인 대상 금액
     */
    @Override
    public int discount(Member member, int price) {

        if (member.getGrade() == Grade.VIP) {
            return discountFicAmount;
        } else {
            return 0;
        }

    }


}
