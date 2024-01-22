package hello.jdbcex.service;

import hello.jdbcex.domain.Member;
import hello.jdbcex.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 트랜잭션 - 파라미터 연동, 풀을 고려한 종료
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV3 {

    private final MemberRepositoryV2 memberRepository;
    private final DataSource         dataSource;


    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Connection con = dataSource.getConnection();
        try {
            //트랜잭션 시작 - autocommit 끄기
            con.setAutoCommit(false);
            bizLogic(con, fromId, toId, money);
            con.commit();   //성공시 commit
        } catch (Exception e) {
            con.rollback();
            throw new IllegalStateException(e);
        } finally {
            release(con);
        }
    }

    private void bizLogic(Connection con, String fromId, String toId, int money) throws SQLException {
        //시작
        Member fromMember = memberRepository.findById(con, fromId);
        Member toMember   = memberRepository.findById(toId);

        memberRepository.update(con, fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(con, toId, toMember.getMoney() + money);
    }

    private static void release(Connection con) {
        if(con != null) {
            try {
                con.setAutoCommit(true);    //커넥션 풀 고려
                con.close();
            } catch (Exception e) {
                log.info("error message", e);
            } finally {

            }
        }
    }

    private static void validation(Member toMember) {
        if(toMember.getMemberId()
                .equals("ex")) {
            throw new IllegalStateException("이체 중 예외 발생");
        }
    }


}
