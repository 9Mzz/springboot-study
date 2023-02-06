package test.testcore.order;

public interface OrderService {

    /**
     * 주문 생성
     *
     * @param memberId
     * @param itemName
     * @param itemPrice
     * @return orderVo
     */
    OrderVo createOrder(Long memberId, String itemName, int itemPrice);

}
