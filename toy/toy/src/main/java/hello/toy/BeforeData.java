package hello.toy;

import hello.toy.items.Items;
import hello.toy.items.ItemsRepository;
import hello.toy.member.Member;
import hello.toy.member.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BeforeData {
    private final MemberRepository memberRepository;
    private final ItemsRepository  itemsRepository;

    @PostConstruct
    public void beforeAct() {
        Items itemA = new Items("itemA", 50200, 30);
        Items itemB = new Items("itemB", 24500, 50);
        Items itemC = new Items("itemC", 9000, 100);
        itemsRepository.save(itemA);
        itemsRepository.save(itemB);
        itemsRepository.save(itemC);


        Member member = new Member("test", "test", "testName");
        memberRepository.save(member);


    }

}
