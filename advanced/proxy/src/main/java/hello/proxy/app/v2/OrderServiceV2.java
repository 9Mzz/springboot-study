package hello.proxy.app.v2;

import hello.proxy.app.v1.OrderServiceV1;

public class OrderServiceV2 implements OrderServiceV1 {

    private final OrderRepositoryV2 orderRepository;

    public OrderServiceV2(OrderRepositoryV2 orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
