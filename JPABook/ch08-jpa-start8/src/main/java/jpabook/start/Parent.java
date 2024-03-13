package jpabook.start;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {
    @Id
    private Long   id;
    private String name;

    @OneToMany(mappedBy = "parent",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

}
