package hello.practice.repository;

import hello.practice.domain.order.OrderFlatDto;
import hello.practice.domain.order.OrderItemQueryDto;
import hello.practice.domain.order.OrderQueryDto;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {


    private final EntityManager em;

    public List<OrderQueryDto> findOrderQueryDtos() {
        List<OrderQueryDto> orders = findOrders();
        orders.forEach(orderQueryDto -> {
            List<OrderItemQueryDto> orderItems = findOrderItems(orderQueryDto.getOrderId());
            orderQueryDto.setOrderItems(orderItems);
        });
        return orders;
    }

    public List<OrderQueryDto> findAllByDto_optimization() {
        // 루트 조회(toOne 코드를 모두 한번에 조회)
        List<OrderQueryDto> orders = findOrders();

        // orderItem 컬렉션을 MAP 한방에 조회
        List<OrderItemQueryDto> orderItemsById = findOrderItemsByOrderId(getOrderIds(orders));

        // 루프를 돌면서 컬렉션 추가(추가 쿼리 실행X)
        orders.forEach(orderQueryDto -> {
            orderQueryDto.setOrderItems(orderItemsById);
        });
        return orders;
    }

    private static List<Long> getOrderIds(List<OrderQueryDto> orders) {
        return orders.stream()
                .map(OrderQueryDto::getOrderId)
                .toList();
    }

    private List<OrderItemQueryDto> findOrderItemsByOrderId(List<Long> getOrderIds) {
        String jpql = "select new hello.practice.domain.order.OrderItemQueryDto(oi.id, i.name, oi.orderPrice, oi.count) " + "from OrderItem oi " + "join oi.item i " + "where oi.id in :orderId";
        return em.createQuery(jpql, OrderItemQueryDto.class)
                .setParameter("orderId", getOrderIds)
                .getResultList();

    }

    /**
     * V6: JPA에서 DTO로 직접 조회, 플랫 데이터 최적화
     */
    public List<OrderFlatDto> findAllByDto_flat() {
        String jpql = "select new hello.practice.domain.order.OrderFlatDto(o.id, m.name, o.orderDate, o.status, d.address, i.name, oi.orderPrice, oi.count) " + "from Order o " + "join o.member m " + "join o.delivery d " + "join o.orderItems oi " + "join oi.item i ";
        return em.createQuery(jpql, OrderFlatDto.class)
                .getResultList();
    }

    public List<OrderQueryDto> findOrders() {
        String jpql = "select new hello.practice.domain.order.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " + "from Order o " + "join o.member m " + "join o.delivery d ";
        return em.createQuery(jpql, OrderQueryDto.class)
                .getResultList();
    }

    public List<OrderItemQueryDto> findOrderItems(Long orderId) {
        String jpql = "select new hello.practice.domain.order.OrderItemQueryDto(oi.id, i.name, oi.orderPrice, oi.count) " + "from OrderItem oi " + "join oi.item i " + "where oi.id = :orderId";
        return em.createQuery(jpql, OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }
}
