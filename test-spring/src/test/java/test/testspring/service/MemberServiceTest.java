package test.testspring.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.testspring.domain.Member;
import test.testspring.repository.MemberMemberRepository;
import test.testspring.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    private static MemberService          memberService;
    private static MemberMemberRepository memberRepository;

    @BeforeEach
    public void testBefore() {
        memberRepository = new MemberMemberRepository();
        memberService    = new MemberService(memberRepository);
    }

    @AfterEach
    public void clearStore() {

        memberRepository.clearStore();

    }


    @Test
    void join() {
        Member member = new Member();
        member.setName("spring");

        Long   saveId = memberService.join(member);
        Member result = memberRepository.findbyId(saveId).get();

        Assertions.assertEquals(member.getName(), result.getName());

    }

    @Test
    public void joinduplicate() {
        //given
        Member member = new Member();
        member.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                                               () -> memberService.join(member2));

        System.out.println("e = " + e);

        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 등록된 회원입니다");


        //then


    }

    @Test
    void findAll() {
    }

    @Test
    void findOne() {
    }
}