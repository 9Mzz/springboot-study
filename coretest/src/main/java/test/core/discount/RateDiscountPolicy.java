package test.core.discount;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import test.core.annotataion.MainDiscountPolicy;
import test.core.member.Grade;
import test.core.member.MemberVo;

@Component
@Primary
//@Qualifier("rateDiscountPolicy")
//@MainDiscountPolicy //MainDiscountPolicy.java
public class RateDiscountPolicy implements DiscountPolicy {


    /**
     * @param memberVo
     * @param price
     * @return 할인 대상 금액
     */
    @Override
    public int discount(MemberVo memberVo, int price) {

        final int discountPercent = 10; //10% 할인

        if (memberVo.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }


}
