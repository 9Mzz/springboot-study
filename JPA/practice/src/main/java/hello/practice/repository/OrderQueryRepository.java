package hello.practice.repository;

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

    public void findOrderQueryDtos() {
        List<OrderQueryDto> orders = findOrders();
        orders.forEach(orderQueryDto -> {
            List<OrderItemQueryDto> orderItems = findOrderItems(orderQueryDto.getOrderId());
            orderQueryDto.setOrderitems(orderItems);
        });

    }

    public List<OrderQueryDto> findOrders() {
        String jpql = "select new hello.practice.domain.order.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " +
                "from Order o " +
                "join o.member m " +
                "join o.delivery d ";
        return em.createQuery(jpql, OrderQueryDto.class)
                .getResultList();

    }

    public List<OrderItemQueryDto> findOrderItems(Long orderId) {
        String jpql = "select new hello.practice.domain.order.OrderItemQueryDto(oi.id, oi.item.name, oi.orderPrice, oi.count) " +
                "from OrderItem oi " +
                "join oi.item i " +
                "where oi.id = :orderId";
        return em.createQuery(jpql, OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();

    }


}
