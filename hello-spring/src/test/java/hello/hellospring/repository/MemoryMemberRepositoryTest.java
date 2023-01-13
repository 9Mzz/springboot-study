package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.lang.model.SourceVersion;
import javax.xml.transform.Result;
import java.util.List;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void clearStore(){

        repository.clearStore();

    }

    @Test
    public void save(){

        Member member = new Member();

        member.setName("spring");

        repository.save(member);

        //Optional은 .get으로 꺼내기 가능
       Member result =  repository.findbyId(member.getId()).get();

        System.out.println("result = " + (result == member));

        Assertions.assertThat(member).isEqualTo(result);

    }

    @Test
    public void findbyName(){

        //g
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //w
        Member result = repository.findByName("spring1").get();
        System.out.println("result = " + result);

        //t
        Assertions.assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){

        //g
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //w
        List<Member> result = repository.findall();

        System.out.println("result = " + result);

        //t
        Assertions.assertThat(result.size()).isEqualTo(2);



    }


}
