package test.testspring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import test.testspring.domain.Member;

import java.util.List;

public class TestMemoryMemberRepository {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void clearStore(){

        repository.clearStore();

    }

    @Test
    public void save() {

        //given
        Member member = new Member();
        member.setName("spring");

        //when
        repository.save(member);

        //then
        Member result = repository.findbyId(member.getId()).get();

        Assertions.assertThat(result).isEqualTo(member);
    }

    @Test
    public void findbyId(){

        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        Member result = repository.findbyName(member2.getName()).get();

        //then
        Assertions.assertThat(member1).isEqualTo(result);


    }

    @Test
    public void findAll(){

        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        System.out.println("result = " + result);

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);


    }


}
