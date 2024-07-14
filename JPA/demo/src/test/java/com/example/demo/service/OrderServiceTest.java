package com.example.demo.service;

import com.example.demo.domain.Address;
import com.example.demo.domain.Item;
import com.example.demo.domain.Member;
import com.example.demo.domain.dto.ItemParam;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemberRepositoryCustom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService     orderService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ItemRepository   itemRepository;

    @Test
    void createOrder() {

        Member memberA = new Member("memberA", 500000, new Address("Seoul", "SaDang", 50002));
        memberRepository.save(memberA);
        Item itemA = new Item("itemA", 2000, 100);
        Item itemB = new Item("itemB", 5500, 100);
        itemRepository.save(itemA);
        itemRepository.save(itemB);

        ItemParam itemParamA = new ItemParam(itemA.getId(), 10);
        ItemParam itemParamB = new ItemParam(itemB.getId(), 50);

        List<ItemParam> itemParamList = new ArrayList<>();
        itemParamList.add(itemParamA);
        itemParamList.add(itemParamB);

        orderService.createOrder(memberA.getId(), itemParamList);


    }
}