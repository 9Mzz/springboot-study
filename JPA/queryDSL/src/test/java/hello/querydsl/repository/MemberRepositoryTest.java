package hello.querydsl.repository;

import hello.querydsl.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class MemberRepositoryTest {

    @Autowired
    EntityManager    em;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void baiscTest() {
        Member memberA = new Member("membrerA", 20);
        memberRepository.save(memberA);

        Member findMember = memberRepository.findById(memberA.getId())
                .get();
        assertThat(memberA)
                .isEqualTo(findMember);
        List<Member> members1 = memberRepository.findAll();
        assertThat(members1)
                .containsExactly(findMember);

        List<Member> members2 = memberRepository.findByUserName(memberA.getUserName());
        assertThat(members2).containsExactly(memberA);

    }


}