package hello.test.service;

import hello.test.domain.Item;
import hello.test.domain.Member;
import hello.test.domain.Order;
import hello.test.domain.OrderItem;
import hello.test.domain.dto.ItemParam;
import hello.test.exception.NoSuchMoneyException;
import hello.test.repository.ItemRepository;
import hello.test.repository.MemberRepository;
import hello.test.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository   itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository  orderRepository;


    public void createOrder(Long memberId, List<ItemParam> itemParams) throws NoSuchMoneyException {
        Member member = memberRepository.findById(memberId)
                .get();
        int             totalPrice = 0;
        List<OrderItem> orderItems = new ArrayList<>();
        for (ItemParam itemParam : itemParams) {
            Long itemId = itemParam.getItemId();
            int  count  = itemParam.getCount();
            Item item = itemRepository.findById(itemId)
                    .get();
            OrderItem orderItem = OrderItem.createOrderItem(item, item.getItemPrice(), count);
            orderItems.add(orderItem);
            totalPrice += orderItem.getTotalOrderPrice();
        }
        Order.createOrder(member, orderItems.toArray(new OrderItem[0]));
        // member 의 Money 감소
        getReduceMoney(member, totalPrice);


    }

    private static void getReduceMoney(Member member, int totalPrice) throws NoSuchMoneyException {
        member.reduceMoney(totalPrice);
    }

}
