package hello.ch10jpql.domain;

import jakarta.persistence.*;
import org.hibernate.event.service.internal.EventListenerRegistryImpl;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String pName;
    private int    price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Order order;

}
