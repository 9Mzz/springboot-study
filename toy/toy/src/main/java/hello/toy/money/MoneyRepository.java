package hello.toy.money;


import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Repository
public class MoneyRepository {

    private final DataSource             dataSource;
    private final SQLExceptionTranslator exceptionTranslator;

    public MoneyRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.exceptionTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
    }

    public Member save(Member member) {
        String            sql   = "insert into member(member_id, money) values(?, ?)";
        Connection        con   = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getMember_id());
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate();
            return member;
        } catch (SQLException e) {
            throw exceptionTranslator.translate("save", sql, e);
        } finally {
            close(con, pstmt, null);
        }
    }

    public Member findById(String member_id) {
        String            sql   = "select * from member where member_id = ?";
        Connection        con   = null;
        PreparedStatement pstmt = null;
        ResultSet         rs    = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member_id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                Member member = new Member();
                member.setMember_id(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else {
                throw new NoSuchElementException("member not found, memberId = " + member_id);
            }
        } catch (SQLException e) {
            throw exceptionTranslator.translate("findById", sql, e);
        } finally {
            close(con, pstmt, rs);
        }
    }

    public void update(String member_id, int money) {
        String            sql   = "update member set money = ? where member_id = ?";
        Connection        con   = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, member_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw exceptionTranslator.translate("update", sql, e);
        } finally {
            close(con, pstmt, null);
        }
    }

    public void delete(String member_id) {
        String            sql   = "delete from member where member_id = ?";
        Connection        con   = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw exceptionTranslator.translate("delete", sql, e);
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
