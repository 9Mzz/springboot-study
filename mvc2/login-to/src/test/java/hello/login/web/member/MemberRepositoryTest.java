package hello.login.web.member;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest {

  MemberRepository memberRepository = new MemberRepository();

  @Test
  void save() {
    Member member2 = new Member();
    member2.setLoginId("test2");
    member2.setPassword("test");
    member2.setName("testName2");

    Member saved      = memberRepository.save(member2);
    Member findByIded = memberRepository.findById(saved.getId());
    Member findByLoginId = memberRepository.findByLoginId("test2")
        .get();

    Assertions.assertThat(saved)
        .isEqualTo(findByIded);

    Assertions.assertThat(findByLoginId.getName())
        .isEqualTo("testName2");

  }

  @Test
  void findById() {
  }

  @Test
  void findAll() {
  }

  @Test
  void findByLoginId() {
  }

  @BeforeEach
  void beforeAct() {
    Member member = new Member();
    member.setLoginId("test");
    member.setPassword("test");
    member.setName("testName");
    memberRepository.save(member);
  }


}