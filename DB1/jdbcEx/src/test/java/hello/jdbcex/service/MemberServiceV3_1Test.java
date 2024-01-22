package hello.jdbcex.service;

import hello.jdbcex.domain.Member;
import hello.jdbcex.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.SQLException;

import static hello.jdbcex.connection.ConnectionConst.*;

/**
 * 파라미터 전달
 */
@Slf4j
class MemberServiceV3_1Test {

    public static final String MEMBER_A  = "memberA";
    public static final String MEMBER_B  = "memberB";
    public static final String MEMBER_EX = "ex";

    private MemberRepositoryV3 memberRepository;
    private MemberServiceV3_1  memberService;

    @BeforeEach
    void before() {
        DriverManagerDataSource    dataSource         = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);

        memberRepository = new MemberRepositoryV3(dataSource);
        memberService = new MemberServiceV3_1(transactionManager, memberRepository);
    }

    @AfterEach
    void after() throws SQLException {
        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
        memberRepository.delete(MEMBER_EX);
    }


    @Test
    void accountTransfer() throws SQLException {
        Member memberA = new Member(MEMBER_A, 10000);
        memberRepository.save(memberA);

        Member memberB = new Member(MEMBER_B, 10000);
        memberRepository.save(memberB);
        //
        memberService.accountTransfer(memberA.getMemberId(), memberB.getMemberId(), 2000);
        Member afterMemberA = memberRepository.findById(memberA.getMemberId());
        Member afterMemberB = memberRepository.findById(memberB.getMemberId());
        System.out.println("afterMemberA = " + afterMemberA);
        System.out.println("afterMemberB = " + afterMemberB);
    }

    @Test
    void accountTransferEx() throws SQLException {
        Member memberA = new Member(MEMBER_A, 10000);
        memberRepository.save(memberA);

        Member memberEx = new Member(MEMBER_EX, 10000);
        memberRepository.save(memberEx);
        //

        Assertions.assertThatThrownBy(() -> memberService.accountTransfer(memberA.getMemberId(), memberEx.getMemberId(), 2000))
                .isInstanceOf(IllegalStateException.class);
        //
        Member findMemberA  = memberRepository.findById(memberA.getMemberId());
        Member findMemberEx = memberRepository.findById(memberEx.getMemberId());
        System.out.println("findMemberA = " + findMemberA);
        System.out.println("findMemberEx = " + findMemberEx);


    }
}