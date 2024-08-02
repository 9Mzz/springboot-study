package hello.test.service;

import hello.test.domain.Item;
import hello.test.domain.Member;
import hello.test.domain.Order;
import hello.test.domain.OrderItem;
import hello.test.domain.dto.OrderCondition;
import hello.test.repository.ItemRepository;
import hello.test.repository.MemberRepository;
import hello.test.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository   itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository  orderRepository;

    public void Order(Long memberId, List<OrderCondition> conditions) {

        Member member = memberRepository.findById(memberId)
                .get();
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderCondition condition : conditions) {
            Long itemId     = condition.getItemId();
            int  orderCount = condition.getOrderCount();

            Item item = itemRepository.findById(itemId)
                    .get();
            OrderItem orderItem = new OrderItem(item, item.getItemPrice(), orderCount);
            orderItems.add(orderItem);
            member.reduceMoney(orderItem.getTotalOrderPrice());
        }
        orderRepository.save(Order.createOrder(member, orderItems.toArray(new OrderItem[0])));
    }


}
