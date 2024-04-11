package hello.practice.domain;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String name;
    private int    price;

    //
    public Item() {
    }

    public Item(String name, int price) {
        this.name  = name;
        this.price = price;
    }
}
