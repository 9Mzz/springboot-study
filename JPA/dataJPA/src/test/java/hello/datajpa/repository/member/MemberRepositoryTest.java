package hello.datajpa.repository.member;

import ch.qos.logback.classic.Logger;
import hello.datajpa.domain.Member;
import jakarta.transaction.Transactional;
import jdk.jfr.StackTrace;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    @Test
    void testMember() {
        // class jdk.proxy2.$Proxy116 (스프링 데이터 JPA가 MemberRepository 인터페이스 보고 프록시 객체(구현체)를 만들어서 주입)
        log.info("memberRepository = " + memberRepository.getClass());


        Member memberA    = new Member("memberA");
        Member saveMember = memberRepository.save(memberA);
        Member findMember = memberRepository.findById(saveMember.getId())
                .get();

        // Result
        log.info("memberA : {}", memberA);
        log.info("saveMember : {}", saveMember);
        log.info("findMember : {}", findMember);

        assertThat(findMember.getId()).isEqualTo(memberA.getId());
        assertThat(findMember.getUserName()).isEqualTo(memberA.getUserName());
        assertThat(findMember).isEqualTo(memberA);   // JPA 동일성 Check

    }

}