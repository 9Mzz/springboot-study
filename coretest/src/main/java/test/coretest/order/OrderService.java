package test.coretest.order;

public interface OrderService {

    OrderVo createOrder(Long memberId, String itemName, int itemPrice);

}
