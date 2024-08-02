package hello.test.service;

import hello.test.domain.Address;
import hello.test.domain.Item;
import hello.test.domain.Member;
import hello.test.domain.dto.MemberDto;
import hello.test.domain.dto.OrderCondition;
import hello.test.repository.ItemRepository;
import hello.test.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired
    ItemRepository   itemRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    OrderService     orderService;
    @Autowired
    MemberService    memberService;
    @Autowired
    EntityManager    entityManager;


    @Test
    void order() {
        Member memberA = new Member("memberA", new Address("Seoul", "Sadang", 2050), 500000);
        memberRepository.save(memberA);

        Item itemA = new Item("itemA", 5000, 50);
        Item itemB = new Item("itemB", 1000, 50);
        itemRepository.save(itemA);
        itemRepository.save(itemB);

        List<OrderCondition> conditions = new ArrayList<>();
        conditions.add(new OrderCondition(itemA.getId(), 5));
        conditions.add(new OrderCondition(itemB.getId(), 10));

        orderService.Order(memberA.getId(), conditions);
        entityManager.flush();
        entityManager.clear();

        //
        MemberDto memberDto = memberService.findMemberDto(memberA.getId());
        log.info("memberDto = {}", memberDto);

    }
}