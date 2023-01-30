package test.testspring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;
import test.testspring.domain.MemberVo;
import test.testspring.repository.MemberRepository;
import test.testspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService          memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeTest() {
        memberRepository = new MemoryMemberRepository();
        memberService    = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterTest(){
        memberRepository.clearStore();
    }

    @Test
    void join() {

        MemberVo test1 = new MemberVo();
        test1.setName("hello");
        Long memberid = memberService.join(test1);

        MemberVo result = memberRepository.findbyId(memberid).get();

        Assertions.assertThat(test1).isEqualTo(result);
    }

    @Test
    public void duplTest() {

        MemberVo test1 = new MemberVo();
        test1.setName("hello");

        MemberVo test2 = new MemberVo();
        test2.setName("hello");

        memberService.join(test1);


        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(test2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

    }

    @Test
    void findOnd() {

        MemberVo test1 = new MemberVo();
        test1.setName("hello");

        MemberVo test2 = new MemberVo();
        test2.setName("hello2");
        memberService.join(test1);

        MemberVo result = memberService.findOnd(test1.getId()).get();

        Assertions.assertThat(test1).isEqualTo(result);

    }

    @Test
    void findAll() {

        List<MemberVo> result = memberService.findAll();


    }
}