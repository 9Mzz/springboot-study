package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void clearStore() {
        memberRepository.clearStore();
    }


    @Test
    void save() {
        //given
        Member member = new Member("hello", 20);
        //when
        Member save = memberRepository.save(member);

        System.out.println("save = " + save);
        //then
        Member findMember = memberRepository.findById(save.getId());

        assertThat(findMember).isEqualTo(save);

    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("userA", 20);
        Member member2 = new Member("userB", 23);


        //when
        memberRepository.save(member1);
        memberRepository.save(member2);


        List<Member> memberList = memberRepository.findAll();

        //then
        assertThat(memberList.size()).isEqualTo(2);
        assertThat(memberList).contains(member1, member2);

    }


}