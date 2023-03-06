package test.core.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import test.core.AutoAppConfig;
import test.core.discount.DiscountPolicy;
import test.core.member.Grade;
import test.core.member.MemberVo;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {

        //생성자 주입 받아서 가져오기
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);

        //테스트 유저 생성
        MemberVo memberVo = new MemberVo(1L, "userA", Grade.VIP);

        // FixDiscountPolicy 이름을 가짐
        int discountPrice = discountService.discount(memberVo, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);


        // RateDiscountPolicy 이름을 가짐
        int rateDiscountPrice = discountService.discount(memberVo, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);


    }


    //static class 생성
    static class DiscountService {


        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy>        policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies  = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        // 할인정책을 수행 할 메소드 생성
        public int discount(MemberVo memberVo, int price, String discountCode) {

            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            System.out.println("method discountPolicy = " + discountPolicy);

            return discountPolicy.discount(memberVo, price);
        }
    }

}
