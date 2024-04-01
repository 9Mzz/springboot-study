package hello.ch05.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {

    @Id
    @Column(name = "orders_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date   ordersDate;
    @Enumerated
    private Status status;

    @ManyToOne
    @JoinColumn(name = "members_id")
    private Members         members;
    @OneToMany(mappedBy = "orders")
    private List<OrderItem> orderItem = new ArrayList<>();

}
