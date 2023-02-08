package test.coretest.discount;

import test.coretest.member.MemberVo;

public interface DiscountPolicy {

    /**
     * memberVo를 받아서 할인정책 시행
     *
     * @param memberVo
     * @param price
     * @return
     */
    int discount(MemberVo memberVo, int price);

}
