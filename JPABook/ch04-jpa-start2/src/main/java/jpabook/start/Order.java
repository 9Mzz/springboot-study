package jpabook.start;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ex_order")
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long          id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "members_id")
    private Members       members;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product       product;
    private int           count;
    private int           price;
    private LocalDateTime localDateTime;

    //
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Members getOrderMember() {
        return members;
    }

    public void setOrderMember(Members members) {
        this.members = members;
    }

    public Product getOrderProduct() {
        return product;
    }

    public void setOrderProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
