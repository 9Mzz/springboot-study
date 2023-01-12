package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.lang.model.SourceVersion;
import javax.xml.transform.Result;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

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

}
