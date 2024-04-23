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


    public List<Order> findAll() {
        String jpql = "select o from Order o join o.member m join o.delivery d";
        return em.createQuery(jpql, Order.class)
                .getResultList();

    }

    public List<Order> findAllWithMemberDelivery() {

        String jpql = "select o from Order o join fetch o.member m join fetch o.delivery d";
        return em.createQuery(jpql, Order.class)
                .getResultList();
    }
}
