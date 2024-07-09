package hello.test.service;

import hello.test.domain.Address;
import hello.test.domain.Item;
import hello.test.domain.Member;
import hello.test.exception.NoSuchMoneyException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService  orderService;
    @Autowired
    MemberService memberService;
    @Autowired
    ItemService   itemService;


    @Test
    void createOrder() throws NoSuchMoneyException {
        Member memberA  = new Member("memberA", 50000, new Address("Seoul", "Sadnag", "InHun-Ro"));
        Long   memberId = memberService.memberSave(memberA);
        Item   itemA    = new Item("itemA", 1500, 50);
        Long   itemId   = itemService.itemSave(itemA);
        orderService.createOrder(memberId, itemId, 12);

    }
}