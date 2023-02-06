package test.testcore.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MemberServiceImplTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {

        MemberVo testA = new MemberVo(1L, "testA", Grade.VIP);
        memberService.join(testA);

        MemberVo result = memberService.findById(testA.getId());
        System.out.println("result = " + result);

        Assertions.assertThat(testA).isEqualTo(result);
    }


}