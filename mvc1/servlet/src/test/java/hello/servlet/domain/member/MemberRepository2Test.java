package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepository2Test {

    MemberRepository2 memberRepository2 = MemberRepository2.getInstance();


    @Test
    void save() {
        //given
        Member2 member2 = new Member2("userA", 20);

        //when
        Member2 save   = memberRepository2.save(member2);
        Member2 result = memberRepository2.findById(save.getId());

        //then
        Assertions.assertThat(save)
                  .isEqualTo(result);

    }


    @Test
    void findAll() {
        //given
        Member2 member1 = new Member2("userA", 20);
        Member2 member2 = new Member2("userB", 23);
        Member2 member3 = new Member2("userC", 21);

        //when
        memberRepository2.save(member1);
        memberRepository2.save(member2);
        memberRepository2.save(member3);

        List<Member2> result = memberRepository2.findAll();

        //then
        Assertions.assertThat(result.size())
                  .isEqualTo(3);

    }

    @AfterEach
    void clearStore() {
        memberRepository2.clearStore();
    }
}