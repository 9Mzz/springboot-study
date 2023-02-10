package test.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.core.Appconfig;

class MemberServiceTest {


    MemberService memberService;

    @BeforeEach
    public void beforeAct() {
        Appconfig appconfig = new Appconfig();
        memberService = appconfig.memberService();
    }

    // 현대적인 코드를 짜려면 테스트는 필수이다.
    @Test
    void join() {
        // given
        MemberVo member = new MemberVo(1L, "memberA", Grade.VIP);

        // when
        memberService.join(member);
        MemberVo findMember = memberService.findMember(1L);

        // then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}