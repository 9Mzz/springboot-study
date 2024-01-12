package hello.totalproject;

import hello.totalproject.items.Items;
import hello.totalproject.items.ItemsRepository;
import hello.totalproject.member.Member;
import hello.totalproject.member.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestInit {

  private final ItemsRepository  itemsRepository;
  private final MemberRepository memberRepository;


  @PostConstruct
  public void postData() {

    itemsRepository.save(new Items("itemA", 15000, 30));
    itemsRepository.save(new Items("itemB", 52000, 60));
    itemsRepository.save(new Items("itemC", 10000, 70));

    Member member = new Member();
    member.setLoginId("test");
    member.setPassword("test");
    member.setName("testName");

    memberRepository.save(member);

  }

}
