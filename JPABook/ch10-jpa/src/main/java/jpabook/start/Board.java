package jpabook.start;

import javax.persistence.*;

//@Entity
public class Board {

    //    @Id
    //    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long   id;
    //    @Column(name = "name", nullable = true)
    private String name;


    //
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
