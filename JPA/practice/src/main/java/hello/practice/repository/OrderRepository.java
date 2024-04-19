package hello.practice.repository;

import hello.practice.domain.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * xxxToOne 관계는 페치 조인해도 페이징에 영향을 주지 않는다.
 * 따라서 xxxToOne 관계는 페치조인으로 쿼리 수를 줄이고 해결하고,
 * 나머지는 hibernate.default_batch_fetch_size 로 최적화 하자.
 */
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


    public List<Order> findAllWithItem() {
        String jpql = "select distinct o FROM Order o " + "join fetch o.member m " + "join fetch o.delivery d " + "join fetch o.orderItems oi " + "join fetch oi.item i";
        return em.createQuery(jpql, Order.class)
                .getResultList();
    }

    public List<Order> findAllWithMemberDelivery() {
        String jpql = "select o from Order o " +
                "join fetch o.member m " +
                "join fetch o.delivery d ";
        return em.createQuery(jpql, Order.class)
                .getResultList();
    }

    public List<Order> findAllWithMemberDeliveryLimitOffset(int offset, int limit) {
        String jpql = "select o from Order o " +
                "join fetch o.member m " +
                "join fetch o.delivery d";
        return em.createQuery(jpql, Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

    }
}
