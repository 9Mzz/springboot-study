package hello.toyex;

import hello.toyex.items.ItemsRepository;
import hello.toyex.items.Items;
import hello.toyex.members.MemberRepository;
import hello.toyex.members.Members;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BeforeSetting {

    private final ItemsRepository  itemsRepository;
    private final MemberRepository memberRepository;


    @PostConstruct
    public void beforeAct() {
        Items itemA = new Items("itemA", 5000, 30);
        Items itemB = new Items("itemB", 43000, 80);
        Items itemC = new Items("itemC", 50300, 100);

        itemsRepository.save(itemA);
        itemsRepository.save(itemB);
        itemsRepository.save(itemC);

        Members memberA = new Members("test", "test", "testName");
        memberRepository.save(memberA);
    }

}
