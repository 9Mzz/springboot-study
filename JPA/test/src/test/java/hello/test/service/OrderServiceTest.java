package hello.test.service;

import hello.test.domain.Address;
import hello.test.domain.Item;
import hello.test.domain.Member;
import hello.test.domain.dto.ItemParam;
import hello.test.exception.NoSuchMoneyException;
import hello.test.repository.ItemRepository;
import hello.test.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.grammars.ordering.OrderingParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Slf4j
class OrderServiceTest {

    @Autowired
    EntityManager    entityManager;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ItemRepository   itemRepository;
    @Autowired
    OrderService     orderService;

    @Test
    void createOrder() {
        Member memberA = new Member("memberA", 100000, new Address("Seoul", "SaDang", 2050));
        Long memberId = memberRepository.save(memberA)
                .getId();

        Item itemA = new Item("itemA", 10000, 50);
        Item itemB = new Item("itemB", 5000, 50);
        Long itemAId = itemRepository.save(itemA)
                .getId();
        Long itemBId = itemRepository.save(itemB)
                .getId();

        entityManager.flush();
        entityManager.clear();

        List<ItemParam> itemParams = new ArrayList<>();
        itemParams.add(new ItemParam(1L, 5));
        itemParams.add(new ItemParam(2L, 10));

        try {
            orderService.createOrder(memberId, itemParams);
        } catch (NoSuchMoneyException e) {
            log.info("잔액이 부족합니다!!!");
        }
        entityManager.flush();
        entityManager.clear();

        Member member = memberRepository.findById(memberId)
                .get();
        log.info("member: {}", member);
        Item findItemA = itemRepository.findById(itemAId)
                .get();
        Item findItemB = itemRepository.findById(itemBId)
                .get();
        log.info("findItemA: {}", findItemA);
        log.info("findItemB: {}", findItemB);


    }
}