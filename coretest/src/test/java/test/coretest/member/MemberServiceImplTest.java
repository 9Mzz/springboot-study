package test.coretest.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.coretest.AppConfig;

class MemberServiceImplTest {

    MemberService memberService;

    @BeforeEach
    public void beforeAct() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void save() {

        MemberVo test = new MemberVo(1L, "testA", Grade.VIP);

        memberService.save(test);

        MemberVo result = memberService.findById(1L);
        System.out.println("test = " + test);
        System.out.println("result = " + result);


        Assertions.assertThat(test).isEqualTo(result);

    }
}