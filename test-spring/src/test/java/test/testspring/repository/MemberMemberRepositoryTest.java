package test.testspring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import test.testspring.domain.Member;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberMemberRepositoryTest {

    MemberMemberRepository repository = new MemberMemberRepository();

    @AfterEach
    void clearStore() {
        repository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when

        //then
        Member result = repository.findbyId(member1.getId()).get();

        Assertions.assertThat(member1).isEqualTo(result);


    }


    @Test
    void findbyName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        Member result = repository.findbyName(member1.getName()).get();

        //then
        Assertions.assertThat(member1).isEqualTo(result);
    }

    @Test
    void fincAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);


        //when
        int size = repository.findAll().size();

        //then
        Assertions.assertThat(size).isEqualTo(2);

    }
}