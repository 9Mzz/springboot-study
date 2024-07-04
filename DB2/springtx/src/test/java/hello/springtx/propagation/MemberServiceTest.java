package hello.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService    memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LogRepository    logRepository;

    /**
     * 트랜잭션 전파 활용2 - 커밋, 롤백
     * MemberService : @Transactional:OFF
     * MemberRepository : @Transactional:ON
     * LogRepository : @Transactional:ON
     */
    @Test
    void outerTxOff_success() {
        //given
        String USERNAME = "outerTxOff_success";

        //when
        memberService.joinV1(USERNAME);

        //then
        assertTrue(memberRepository.find(USERNAME)
                           .isPresent());
        assertTrue(logRepository.find(USERNAME)
                           .isPresent());
    }


    /**
     * 트랜잭션 전파 활용2 - 커밋, 롤백
     * MemberService @Transactional:OFF
     * MemberRepository @Transactional:ON
     * LogRepository @Transactional:ON Exception
     */
    @Test
    void outerTxOff_Fail() {
        //given
        String USERNAME = "로그예외_OuterTxOff_Fail";

        //when
        assertThatThrownBy(() -> memberService.joinV1(USERNAME)).isInstanceOf(RuntimeException.class);
        //then
        assertTrue(memberRepository.find(USERNAME)
                           .isPresent());
        assertTrue(logRepository.find(USERNAME)
                           .isEmpty());
    }

    /**
     * 트랜잭션 전파 활용3 - 단일 트랜잭션
     * MemberService @Transactional:ON
     * MemberRepository @Transactional:OFF
     * LogRepository @Transactional:OFF
     */
    @Test
    void singleTx() {
        //given
        String USERNAME = "singleTx";
        //when
        memberService.joinV1(USERNAME);
        //then
        Assertions.assertTrue(memberRepository.find(USERNAME)
                                      .isPresent());
        Assertions.assertTrue(logRepository.find(USERNAME)
                                      .isPresent());


    }

    /**
     * 트랜잭션 전파 활용4 - 전파 커밋
     * MemberService @Transactional:ON
     * MemberRepository @Transactional:ON
     * LogRepository @Transactional:ON
     */
    @Test
    void outerTxOn_success() {
        //given
        String USERNAME = "outerTxOn_success";

        //when
        memberService.joinV1(USERNAME);
        //then
        Assertions.assertTrue(memberRepository.find(USERNAME)
                                      .isPresent());
        Assertions.assertTrue(logRepository.find(USERNAME)
                                      .isPresent());
    }

    /**
     * 트랜잭션 전파 활용5 - 전파 롤백
     * MemberService @Transactional:ON
     * MemberRepository @Transactional:ON
     * LogRepository @Transactional:ON Exception
     */
    @Test
    void outerTxOn_fail() {
        //given
        String username = "로그예외_outerTxOn_fail";
        //when
        assertThatThrownBy(() -> memberService.joinV1(username)).isInstanceOf(RuntimeException.class);
        //then: 모든 데이터가 롤백된다.
        assertTrue(memberRepository.find(username)
                           .isEmpty());
        assertTrue(logRepository.find(username)
                           .isEmpty());
    }

    /**
     * 트랜잭션 전파 활용6 - 복구 REQUIRED
     * MemberService @Transactional:ON
     * MemberRepository @Transactional:ON
     * LogRepository @Transactional:ON Exception
     */
    @Test
    void recoverException_Fail() {
        //given
        String username = "로그예외_recoverException_Fail";
        //when
        assertThatThrownBy(() -> memberService.joinV2(username)).isInstanceOf(UnexpectedRollbackException.class);
        //then: 모든 데이터가 롤백된다.
        assertTrue(memberRepository.find(username)
                           .isEmpty());
        assertTrue(logRepository.find(username)
                           .isEmpty());
    }

    /**
     * 트랜잭션 전파 활용7 - 복구 REQUIRES_NEW
     * MemberService @Transactional:ON
     * MemberRepository @Transactional:ON
     * LogRepository @Transactional(REQUIRES_NEW) Exception
     */
    @Test
    void recoverException_success() {
        //given
        String username = "로그예외_recoverException_Success";

        //when
        memberService.joinV2(username);
        //then member 저장, log 롤백
        assertTrue(memberRepository.find(username)
                           .isPresent());
        assertTrue(logRepository.find(username)
                           .isEmpty());
    }
}