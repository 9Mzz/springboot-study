package hello.core.order;

public interface OrderService {

    /**
     * 주문 생성
     * @param memberId, @param itemName, @param itemPrice
     * @return Order
     */
    Order createOrder(Long memberId, String itemName, int itemPrice);

}
