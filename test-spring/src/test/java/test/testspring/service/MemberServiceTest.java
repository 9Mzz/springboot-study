package test.testspring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.testspring.domain.Member;
import test.testspring.repository.MemoryMemberRepository;

import java.security.Provider;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService    = new MemberService(memberRepository);

    }

    @AfterEach
    public void clearStore() {
        memberRepository.clearStore();
    }

    @DisplayName("회원 가입")
    @Test
    void join() {

        Member member = new Member();
        member.setName("spring");

        Long join = memberService.join(member);

        Member result = memberRepository.findbyId(join).get();

        Assertions.assertThat(member.getName()).isEqualTo(result.getName());
    }

    @Test
    public void duplicateCheck() {

        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        Long join = memberService.join(member1);


        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        System.out.println("e = " + e);

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    void findAll() {
    }

    @Test
    void findOne() {
    }
}