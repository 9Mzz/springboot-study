package jpabook.start;

import javax.persistence.*;

@Entity
public class Child {

    @Id
    private Long   id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id",
                nullable = true)
    private Parent parent;


}
