package test.testspring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import test.testspring.domain.Member;

import java.util.List;

public class MemberMemberRepositoryTest {

    MemberMemberRepository repository = new MemberMemberRepository();

    @AfterEach
    public void clearStore(){
        repository.clearStore();
    }

    @Test
    public void test2(){

        Member member1 = new Member();
        member1.setName("spring1");

        repository.save(member1);

        Member result = repository.findbyName(member1.getName()).get();

        Assertions.assertThat(member1).isEqualTo(result);

    }

    @Test
    public void save() {

        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findbyId(member.getId()).get();

        System.out.println("result = " + result);

        Assertions.assertThat(member).isEqualTo(result);

    }

    @Test
    public void findbyId() {

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member result = repository.findbyName(member1.getName()).get();


        Assertions.assertThat(member1).isEqualTo(result);

    }

    @Test
    public void findAll() {

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        System.out.println("result = " + result);

    }


}
