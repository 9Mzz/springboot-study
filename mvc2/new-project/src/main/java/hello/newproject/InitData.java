package hello.newproject;

import hello.newproject.repository.ItemRepository;
import hello.newproject.repository.MemberRepsitory;
import hello.newproject.vo.Item;
import hello.newproject.vo.Member;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitData {

  private final ItemRepository  itemRepository;
  private final MemberRepsitory memberRepsitory;

  @PostConstruct
  public void postData() {
    itemRepository.save(new Item("testA", 10000, 30));
    itemRepository.save(new Item("testB", 4000, 500));
    memberRepsitory.save(new Member("test", "test", "testName"));
  }

}
