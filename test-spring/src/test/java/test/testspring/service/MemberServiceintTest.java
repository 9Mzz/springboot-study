package test.testspring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import test.testspring.domain.Member;
import test.testspring.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceintTest {

    @Autowired
    private MemberService    memberService;
    @Autowired
    private MemberRepository repository;

    @Test
    void join() {

        Member member1 = new Member();
        member1.setName("spring1");
        Long memberId = memberService.join(member1);

        Member result = memberService.findOne(memberId).get();

        Assertions.assertThat(member1).isEqualTo(result);
    }

    @Test
    public void dupCheck() {
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring1");

        memberService.join(member1);


        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

    }

    @Test
    void findAll() {


    }

}