package test.testspring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.testspring.domain.Member;
import test.testspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void before() {

        memoryMemberRepository = new MemoryMemberRepository();

        memberService = new MemberService(memoryMemberRepository);

    }

    @AfterEach
    public void clearStore() {
        memoryMemberRepository.clearStore();
    }


    @DisplayName("회원가입 테스트")
    @Test
    void join() {

        Member member = new Member();
        member.setName("spring");

        Long join = memberService.join(member);

        Member result = memberService.findOne(join).get();
        System.out.println("result = " + result);

        Assertions.assertThat(result.getName()).isEqualTo(member.getName());


    }

    @DisplayName("회원가입 중복 테스트")
    @Test
    public void duplicateTest() {
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);


        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        System.out.println("e = " + e);

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    void findAll() {

        Member member1 = new Member();
        member1.setName("spring");
        memberService.join(member1);

        List<Member> result = memberService.findAll();
        Assertions.assertThat(result.size()).isEqualTo(1);

    }

    @Test
    void findOne() {
        Member member1 = new Member();
        member1.setName("spring");
        memberService.join(member1);

        Member result = memberService.findOne(member1.getId()).get();

        System.out.println("result = " + result);

    }
}