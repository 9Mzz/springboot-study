package hello.ch05.model;

import jakarta.persistence.*;
import lombok.extern.apachecommons.CommonsLog;

@Entity
public class Item {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;
    private String  name;
    private Integer price;
    private Integer stockQuantity;

}
