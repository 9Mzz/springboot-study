package hello.ch10.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String productName;
    private int    price;
    private int    stockAmount;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

}
