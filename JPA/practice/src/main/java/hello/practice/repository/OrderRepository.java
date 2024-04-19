package hello.practice.repository;

import hello.practice.domain.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Transactional
@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public Order save(Order order) {
        log.info("Saving order: {}", order);
        em.persist(order);
        return order;
    }

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }


/*
    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery(
                        "select new hello.practice.repository.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                                "from Order o" +
                                " join o.member m" +
                                " join o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
    }
*/

    public List<Order> findAllByAddressDelivery() {
        String jpql = "select o From Order o join fetch o.member m join fetch o.delivery d";
        return em.createQuery(jpql, Order.class)
                .getResultList();
    }

    public List<Order> findAllWithItem() {
        String jpql = "select o from Order o " + "join fetch o.member m " + "join fetch o.delivery d " + "join fetch o.orderItems oi " + "join fetch oi.item i ";
        return em.createQuery(jpql, Order.class)
                .getResultList();
    }

    public List<Order> findallwithMemberDelivery(int offset, int limit) {
        String jpql = "select o from Order o join fetch o.member m join fetch o.delivery d";
        return em.createQuery(jpql, Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

    }
}
