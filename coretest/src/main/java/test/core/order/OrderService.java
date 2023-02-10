package test.core.order;

public interface OrderService {

    //주문 결과를 OrderVo로 반환
    OrderVo createOrder(Long memberId, String itemName, int itemPrice);

}
