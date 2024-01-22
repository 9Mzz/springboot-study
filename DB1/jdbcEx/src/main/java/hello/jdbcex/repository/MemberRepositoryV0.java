package hello.jdbcex.repository;

import hello.jdbcex.connection.DBConnectionUtil;
import hello.jdbcex.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

@Slf4j
public class MemberRepositoryV0 {

    /**
     * 회원 저장
     */
    public Member save(Member member) throws SQLException {

        String            sql   = "insert into member(member_id, money) values (?, ?)";
        Connection        con   = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate();
            return member;
        } catch (SQLException e) {
            log.info("db error e", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }
    }

    /**
     * memberId로 회원 검색
     */
    public Member findById(String loginId) throws SQLException {
        String            sql   = "select * from member where member_id = ?";
        Connection        con   = null;
        PreparedStatement pstmt = null;
        ResultSet         rs    = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, loginId);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else {
                throw new NoSuchElementException("member not found, memberId = " + loginId);
            }
        } catch (SQLException e) {
            log.info("db error e", e);
            throw e;
        } finally {
            close(con, pstmt, rs);
        }
    }

    /**
     * 회원 수정
     */
    public void update(String memberId, int money) throws SQLException {
        String            sql   = "update member set money = ? where member_id = ?";
        Connection        con   = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);
            int result = pstmt.executeUpdate();
            log.info("update result = {}", result);
        } catch (SQLException e) {
            log.info("db error e", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }
    }

    /**
     * 삭제
     */
    public void delete(String memberId) throws SQLException {
        String            sql   = "delete from member where member_id = ?";
        Connection        con   = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            int result = pstmt.executeUpdate();
            log.info("delete result = {}", result);
        } catch (SQLException e) {
            log.info("db error e", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }


    }


    private static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("error", e);
            }
        }
        if(pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                log.error("error", e);
            }
        }

        if(con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("error", e);
            }
        }


    }

    private static Connection getConnection() throws SQLException {
        return DBConnectionUtil.getConnection();
    }
}
