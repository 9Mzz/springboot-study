package jpabook.start.item;

import javax.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "DTYPE")
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long   id;
    private String name;
    private int    price;

    //
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    //

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + '}';
    }
}
