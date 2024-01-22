package hello.jdbcex.service;

import hello.jdbcex.domain.Member;
import hello.jdbcex.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import java.sql.SQLException;

/**
 * 트랜잭션 - 파라미터 연동, 풀을 고려한 종료
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV3_1 {


    //    private final DataSource         dataSource;
    private final PlatformTransactionManager transactionManager;
    private final MemberRepositoryV3         memberRepository;


    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        //트랜잭션 시작
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionAttribute());



        try {
            //트랜잭션 시작 - autocommit 끄기
            bizLogic(fromId, toId, money);
            transactionManager.commit(status);
        } catch (Exception e) {
            //실패시 롤백
            transactionManager.rollback(status);
            throw new IllegalStateException(e);
        }
    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        //시작
        Member fromMember = memberRepository.findById(fromId);
        Member toMember   = memberRepository.findById(toId);

        memberRepository.update(fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(toId, toMember.getMoney() + money);
    }

    private static void validation(Member toMember) {
        if(toMember.getMemberId()
                .equals("ex")) {
            throw new IllegalStateException("이체 중 예외 발생");
        }
    }


}
