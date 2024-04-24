package hello.practice.repository;

import hello.practice.domain.OrderItem;
import hello.practice.domain.order.OrderFlatDto;
import hello.practice.domain.order.OrderItemQueryDto;
import hello.practice.domain.order.OrderQueryDto;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;

    /**
     * 컬렉션은 별도로 조회
     * Query: 루트 1번, 컬렉션 N 번
     * 단건 조회에서 많이 사용하는 방식
     */
    public List<OrderQueryDto> findOrderQueryDtos() {
        // 루트 조회(toOne 코드를 모두 한번에 조회) : 1번
        List<OrderQueryDto> result = getOrderQuery();
        // 루프를 돌면서 컬렉션 추가(추가 쿼리 실행) : n번
        result.forEach(o -> {
            List<OrderItemQueryDto> orderItems = getOrderItemsQuery(o.getOrderId());
            o.setOrderItems(orderItems);
        });
        return result;
    }

    /**
     * 최적화
     * Query: 루트 1번, 컬렉션 1번
     * 데이터를 한꺼번에 처리할 때 많이 사용하는 방식
     */
    public List<OrderQueryDto> findAllByDto_optimization() {

        // 루트 조회(toOne 코드를 모두 한번에 조회)
        List<OrderQueryDto> result = getOrderQuery();

        // orderItem 컬렉션을 MAP 한방에 조회
        Map<Long, List<OrderItemQueryDto>> orderItemMap = getOrderItems(getOrderId(result));

        // 루프를 돌면서 컬렉션 추가(추가 쿼리 실행X)
        result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));

        return result;
    }

    public List<OrderFlatDto> findAllByDto_flat() {
        String jpql = "select new hello.practice.domain.order.OrderFlatDto(o.id, m.name, o.orderDate, d.address, o.status, i.name, oi.orderPrice, oi.count) " + "from Order o " + "join o.member m " + "join o.delivery d " + "join o.orderItems oi " + "join oi.item i";
        return em.createQuery(jpql, OrderFlatDto.class)
                .getResultList();

    }

    private Map<Long, List<OrderItemQueryDto>> getOrderItems(List<Long> list) {
        String jpql = "select new hello.practice.domain.order.OrderItemQueryDto(oi.id, i.name, oi.orderPrice, oi.count) " + "from OrderItem oi " + "join oi.item i " + "where oi.id in :ids";
        List<OrderItemQueryDto> orderItems = em.createQuery(jpql, OrderItemQueryDto.class)
                .setParameter("ids", list)
                .getResultList();
        return orderItems.stream()
                .collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));
    }

    private static List<Long> getOrderId(List<OrderQueryDto> orderQuery) {
        return orderQuery.stream()
                .map(OrderQueryDto::getOrderId)
                .toList();
    }

    /**
     * 1:N 관계인 orderItems 조회
     */
    private List<OrderItemQueryDto> getOrderItemsQuery(Long orderId) {
        String jpql = "select new hello.practice.domain.order.OrderItemQueryDto(oi.id, i.name, oi.orderPrice, oi.count) " + "from OrderItem oi " + "join oi.item i " + "where oi.id = :orderId";
        return em.createQuery(jpql, OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    /**
     * 1:N 관계(컬렉션)를 제외한 나머지를 한번에 조회
     */
    private List<OrderQueryDto> getOrderQuery() {
        String jpql = "select new hello.practice.domain.order.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " + "from Order o " + "join o.member m " + "join o.delivery d";
        return em.createQuery(jpql, OrderQueryDto.class)
                .getResultList();
    }
}
