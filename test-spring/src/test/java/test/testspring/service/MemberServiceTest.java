package test.testspring.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.core.AbstractMasterDetailListProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import test.testspring.domain.Member;
import test.testspring.repository.MemberRepository;
import test.testspring.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService    memberService;
    @Autowired
    private MemberRepository repository;


    /**
     * 회원가입 테스트
     */
    @Test
    void join() {

        Member test1 = new Member();
        test1.setName("spring");

        Long   memberId = memberService.join(test1);
        Member result   = memberService.findOne(memberId).get();

        assertThat(test1.getName()).isEqualTo(result.getName());
    }

    /**
     * 회원가입 중복 테스트
     */
    @Test
    @Commit
    public void dupliTest() {

        Member test1 = new Member();
        test1.setName("spring");

        Member test2 = new Member();
        test2.setName("spring");

        memberService.join(test1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(test2));
        System.out.println("e = " + e);

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }

}