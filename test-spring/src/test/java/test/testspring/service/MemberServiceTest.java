package test.testspring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;
import test.testspring.domain.MemberVo;
import test.testspring.repository.MemoryMemberRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService          memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeAct() {
        memberRepository = new MemoryMemberRepository();
        memberService    = new MemberService(memberRepository);
    }

    @AfterEach
    public void clearStore() {
        memberRepository.clearStore();
    }

    @Test
    void join() {

        MemberVo test1 = new MemberVo();
        test1.setName("spring");

        Long     memberId = memberService.join(test1);
        MemberVo result   = memberRepository.findbyId(memberId).get();

        System.out.println("result = " + result);

        Assertions.assertThat(result.getName()).isEqualTo(test1.getName());
    }

    @DisplayName("중복 테스트")
    @Test
    public void duplicateJoin() {

        MemberVo test1 = new MemberVo();
        test1.setName("spring");

        MemberVo test2 = new MemberVo();
        test2.setName("spring");

        memberService.join(test1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(test2));
        System.out.println("e = " + e);

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @DisplayName("모두 검색")
    @Test
    void findAll() {

        MemberVo test1 = new MemberVo();
        test1.setName("spring");

        MemberVo test2 = new MemberVo();
        test2.setName("spring2");

        memberService.join(test1);
        memberService.join(test2);

        List<MemberVo> result = memberService.findAll();


        Assertions.assertThat(result.size()).isEqualTo(2);

    }

    @Test
    void findOne() {

        MemberVo test1 = new MemberVo();
        test1.setName("spring");

        MemberVo test2 = new MemberVo();
        test2.setName("spring2");

        memberService.join(test1);
        memberService.join(test2);

        MemberVo result = memberService.findOne(test1.getId()).get();

        Assertions.assertThat(result).isEqualTo(test1);
    }
}