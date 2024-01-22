package hello.jdbcex.repository;

import hello.jdbcex.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();


    @Test
    void crud() throws SQLException {
        Member member = new Member("memberV1", 10000);

        //save
        Member saveReuslt = repository.save(member);
        Assertions.assertThat(member)
                .isEqualTo(saveReuslt);

        //findById
        Member findResult = repository.findById(member.getMemberId());
        Assertions.assertThat(findResult)
                .isEqualTo(member);

        //update
        repository.update(member.getMemberId(), 50000);
        Member updateMember = repository.findById(member.getMemberId());
        Assertions.assertThat(updateMember.getMoney())
                .isEqualTo(50000);

        //delete
        repository.delete(member.getMemberId());
        Assertions.assertThatThrownBy(() -> repository.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);


    }
}