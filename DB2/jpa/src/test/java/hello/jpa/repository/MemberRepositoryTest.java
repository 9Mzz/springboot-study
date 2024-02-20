package hello.jpa.repository;

import hello.jpa.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    @Test
    void save() {
        //given
        Member item = new Member("itemA", 10000);

        //when
        Member result = memberRepository.save(item);

        //then
        Member findItem = memberRepository.findById(item.getId()).get();
        assertThat(findItem).isEqualTo(result);
    }

}