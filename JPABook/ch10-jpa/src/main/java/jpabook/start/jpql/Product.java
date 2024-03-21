package jpabook.start.jpql;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @Column(name = "product_id")
    private Long   id;
    private String product_name;
    private int    price;
    private int    stockAmout;


    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;

    //
    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockAmout() {
        return stockAmout;
    }

    public void setStockAmout(int stockAmout) {
        this.stockAmout = stockAmout;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
