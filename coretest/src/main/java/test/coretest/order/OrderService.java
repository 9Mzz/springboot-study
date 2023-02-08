package test.coretest.order;

public interface OrderService {

    /**
     * 주문내역 생성
     * @param memberId
     * @param itemName
     * @param itemPrice
     * @return
     */
    OrderVo createOrder(Long memberId, String itemName, int itemPrice);

}
