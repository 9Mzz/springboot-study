package hello.jdbcex.repository;

import hello.jdbcex.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

@Repository
public class MemberRepository {


    private final DataSource             dataSource;
    private final SQLExceptionTranslator txTranslator;

    public MemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.txTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
    }

    public Member save(Member member) {
        String            sql   = "insert into member values(?, ?)";
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
            throw txTranslator.translate("save", sql, e);
        } finally {
            close(con, pstmt, null);
        }
    }

    public Member findById(String memberId) {
        String            sql   = "select * from member where member_id = ?";
        Connection        con   = null;
        PreparedStatement pstmt = null;
        ResultSet         rs    = null;


        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else {
                throw new NoSuchElementException("member not found, member_id = " + memberId);
            }
        } catch (SQLException e) {
            throw txTranslator.translate("findById", sql, e);
        } finally {
            close(con, pstmt, rs);
        }
    }

    public void update(String memberId, int money) {
        String            sql   = "update member set money = ? where member_id = ?";
        Connection        con   = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            txTranslator.translate("update", sql, e);
        } finally {
            close(con, pstmt, null);
        }
    }

    public void delete(String memberId) {
        String            sql   = "delete from member where member_id = ?";
        Connection        con   = null;
        PreparedStatement pstmt = null;


        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            txTranslator.translate("update", sql, e);
        } finally {
            close(con, pstmt, null);
        }

    }


    private void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(pstmt);
        DataSourceUtils.releaseConnection(con, dataSource);
    }


    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

}
