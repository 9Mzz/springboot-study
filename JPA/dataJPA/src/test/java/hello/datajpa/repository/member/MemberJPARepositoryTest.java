package hello.datajpa.repository.member;

import hello.datajpa.domain.Member;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class MemberJPARepositoryTest {

    @Autowired
    MemberJPARepository repository;


    @Test
    @DisplayName("JPA 기반 테스트")
    void crudTest() {
        Member memberA    = new Member("memberA");
        Member saveMember = repository.save(memberA);
        Member findMember = repository.findById(saveMember.getId())
                .get();

        assertThat(saveMember).isEqualTo(findMember);
        assertThat(saveMember.getId()).isEqualTo(findMember.getId());
        assertThat(saveMember.getUserName()).isEqualTo(findMember.getUserName());

        log.info("memberA.getCreateBy() = {}", memberA.getCreateBy());
        log.info(" memberA.getModifyBy() = {}", memberA.getModifyBy());
    }

    @Test
    void basicCrud() {
        Member memberA = new Member("memberA");
        repository.save(memberA);
        Member memberB = new Member("memberB");
        repository.save(memberB);

        Member findMemberA = repository.findById(memberA.getId())
                .get();
        Member findMemberB = repository.findById(memberB.getId())
                .get();

        assertThat(memberA).isEqualTo(findMemberA);
        assertThat(memberB).isEqualTo(findMemberB);

        findMemberA.setUserName("memberAAAAAAAAAAAAAAA");


        // 리스트 조회 검증
        List<Member> all = repository.findAll();
        assertThat(all.size()).isEqualTo(2);
        // Count 검증
        assertThat(repository.getCount()).isEqualTo(2);
        // Delete 검증
        repository.delete(findMemberA);
        repository.delete(findMemberB);
        assertThat(repository.getCount()).isEqualTo(0);


    }

}