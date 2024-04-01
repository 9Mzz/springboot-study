package hello.ch05.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Members {

    @Id
    @Column(name = "members_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;
    private String  name;
    private String  City;
    private String  Street;
    private Integer ZIpCode;

    @OneToMany(mappedBy = "members")
    private List<Orders> orders = new ArrayList<>();

}
