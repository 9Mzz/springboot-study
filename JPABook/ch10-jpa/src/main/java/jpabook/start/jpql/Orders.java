package jpabook.start.jpql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orders {


    @Id
    @Column(name = "orders_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int  orderAmount;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "orders")
    private List<Product> products = new ArrayList<>();

    ///
    public Orders() {
    }

    public Orders(int orderAmount, Member member, Address address, List<Product> products) {
        this.orderAmount = orderAmount;
        this.member      = member;
        this.address     = address;
        this.products    = products;
    }

    public Orders(Long id, int orderAmount, Member member, Address address, List<Product> products) {
        this.id          = id;
        this.orderAmount = orderAmount;
        this.member      = member;
        this.address     = address;
        this.products    = products;
    }

    //
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
