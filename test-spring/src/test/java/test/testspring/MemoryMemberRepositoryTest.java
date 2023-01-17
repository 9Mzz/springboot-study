package test.testspring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import test.testspring.domain.Member;
import test.testspring.repository.MemoryMemberRepository;

import java.util.List;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void clearStore(){
        repository.clearStore();
    }

    @Test
    public void save() {

        Member member1 = new Member();
        member1.setName("testSave");
        repository.save(member1);

    }

    @Test
    public void findName() {
        Member member1 = new Member();
        member1.setName("testSave");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("testSave2");
        repository.save(member2);

        Member result = repository.findbyName("testSave2").get();

        System.out.println("result = " + result);

        Assertions.assertThat(member2).isEqualTo(result);

    }

    @Test
    public void findAll() {

        Member member1 = new Member();
        member1.setName("testSave");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("testSave2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }


}
