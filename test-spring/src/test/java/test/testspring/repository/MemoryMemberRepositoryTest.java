package test.testspring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import test.testspring.domain.Member;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @Test
    void save() {

        Member test1 = new Member();
        test1.setName("spring");
        repository.save(test1);

        Member result = repository.findbyId(test1.getId()).get();
        Assertions.assertThat(test1).isEqualTo(result);

    }

    @Test
    void findbyName() {

        Member test1 = new Member();
        test1.setName("spring");
        repository.save(test1);

        Member test2 = new Member();
        test2.setName("spring2");
        repository.save(test2);

        Member result = repository.findbyName("spring").get();

        Assertions.assertThat(test1.getName()).isEqualTo(result.getName());

    }

    @Test
    void findAll() {
        Member test1 = new Member();
        test1.setName("spring");
        repository.save(test1);

        Member test2 = new Member();
        test2.setName("spring2");
        repository.save(test2);


        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);

    }
}