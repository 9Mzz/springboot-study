package com.example.demo.service;

import com.example.demo.domain.Item;
import com.example.demo.domain.Member;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.dto.ItemParam;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemberRepositoryCustom;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository         itemRepository;
    private final MemberRepository       memberRepository;
    private final MemberRepositoryCustom memberRepositoryCustom;
    private final OrderRepository        orderRepository;


    /**
     * 주문 생성
     */
    public void createOrder(Long memberId, List<ItemParam> itemParamList) {
        // 회원 찾기
        Member member = memberRepository.findById(memberId)
                .get();
        List<OrderItem> orderItems = new ArrayList<>();
        int             totalPrice = 0;

        for (ItemParam itemParam : itemParamList) {
            Long itemId    = itemParam.getItemId();
            int  itemCount = itemParam.getItemCount();
            Item item = itemRepository.findById(itemId)
                    .get();
            OrderItem orderItem = OrderItem.createOrderItem(item, item.getItemPrice(), itemCount);
            orderItems.add(orderItem);
            totalPrice += orderItem.getTotalOrderPrice();
        }
        Order order = Order.createOrder(member, orderItems.toArray(new OrderItem[0]));
        orderRepository.save(order);
        memberRepositoryCustom.reduceMoney(member.getId(), totalPrice);
    }

}
