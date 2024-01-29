package hello.jdbcex.repository;

import hello.jdbcex.domain.Member;
import hello.jdbcex.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;

import javax.sql.DataSource;
import java.util.Random;

@Slf4j
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @TestConfiguration
    static class testConfig {

        private final DataSource dataSource;

        public testConfig(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Bean
        MemberRepository memberRepository() {
            return new MemberRepository(dataSource);
        }

        @Bean
        MemberService memberService() {
            return new MemberService(memberRepository());
        }
    }

    private static final String MEMBER_A  = "memberA";
    private static final String MEMBER_B  = "memberB";
    private static final String MEMBER_EX = "ex";

    @AfterEach
    void after() {
        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
        memberRepository.delete(MEMBER_EX);
    }


    @Test
    void save() {
        Member memberA = new Member(MEMBER_A, 20000);
        //save
        Member saveMemberA = memberRepository.save(memberA);
        Assertions.assertThat(memberA)
                .isEqualTo(saveMemberA);
        //findById
        Member findMemberA = memberRepository.findById("memberA");
        Assertions.assertThat(memberA)
                .isEqualTo(findMemberA);
        //update
        Member updateMemberA = new Member(MEMBER_A, 50000);
        memberRepository.update(updateMemberA.getMemberId(), updateMemberA.getMoney());
        Member updatedMemberA = memberRepository.findById(updateMemberA.getMemberId());
        Assertions.assertThat(updatedMemberA.getMoney())
                .isEqualTo(50000);
    }

    @Test
    void saveEx() {
        Member memberA      = new Member(MEMBER_A, 20000);
        Member memberAClone = new Member(MEMBER_A, 20000);
        Member saveMemberA  = memberRepository.save(memberA);
        log.info("saveMemberA = {}", saveMemberA);

        try {
            memberRepository.save(memberAClone);
        } catch (DuplicateKeyException e) {
            String newMemberAClone = memberAClone.getMemberId() + new Random().nextInt(1000);
            Member newMember       = new Member(newMemberAClone, memberAClone.getMoney());
            memberRepository.save(newMember);
            log.info("new Member = {}", newMember);
        }
    }

    @Test
    void transfer() {
        memberRepository.save(new Member(MEMBER_A, 20000));
        memberRepository.save(new Member(MEMBER_B, 20000));
        memberService.moneyTransfer(MEMBER_A, MEMBER_B, 2000);
        Member resultMemberA = memberRepository.findById(MEMBER_A);
        Member resultMemberB = memberRepository.findById(MEMBER_B);
        log.info("resultMemberA = {}", resultMemberA);
        log.info("resultMemberB = {}", resultMemberB);
    }

    @Test
    void transferEx() {
        memberRepository.save(new Member(MEMBER_A, 20000));
        memberRepository.save(new Member(MEMBER_EX, 20000));
        Assertions.assertThatThrownBy(() -> {
                    memberService.moneyTransfer(MEMBER_A, MEMBER_EX, 2000);
                })
                .isInstanceOf(IllegalStateException.class);
        Member resultMemberA  = memberRepository.findById(MEMBER_A);
        Member resultMemberEX = memberRepository.findById(MEMBER_EX);
        log.info("resultMemberA = {}", resultMemberA);
        log.info("resultMemberEX = {}", resultMemberEX);
    }

}