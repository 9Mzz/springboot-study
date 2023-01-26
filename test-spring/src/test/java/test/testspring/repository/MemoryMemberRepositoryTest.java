package test.testspring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import test.testspring.domain.Member;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    void clearStore() {
        repository.clearStore();
    }

    @Test
    void save() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findbyId(member2.getId()).get();

        Assertions.assertThat(result).isEqualTo(member2);
    }

    @Test
    void findbyName() {

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findbyName(member1.getName()).get();

        Assertions.assertThat(result).isEqualTo(member1);

    }

    @Test
    void findAll() {

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();


        System.out.println("findAll result = " + result);

        Assertions.assertThat(result.size()).isEqualTo(2);


    }
}