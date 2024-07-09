package hello.test.service;

import hello.test.domain.Item;
import hello.test.domain.Member;
import hello.test.domain.Order;
import hello.test.domain.OrderItem;
import hello.test.exception.NoSuchMoneyException;
import hello.test.repository.item.ItemRepository;
import hello.test.repository.member.MemberRepository;
import hello.test.repository.member.MemberRepositoryCustom;
import hello.test.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository       memberRepository;
    private final MemberRepositoryCustom memberRepositoryCustom;
    private final ItemRepository         itemRepository;
    private final OrderRepository        orderRepository;

    public void createOrder(Long memberId, Long itemId, int count) throws NoSuchMoneyException {

        Member member = memberRepository.findById(memberId)
                .get();
        Item item = itemRepository.findById(itemId)
                .get();
        // orderItems 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        // order 생성
        Order order = Order.createOrder(member, orderItem);
        // 사용자 money 감소
        int resultMoney = member.removeMoney(orderItem.totalPrice());
        memberRepositoryCustom.updateMemberMoney(resultMoney);

        orderRepository.save(order);
    }

}
