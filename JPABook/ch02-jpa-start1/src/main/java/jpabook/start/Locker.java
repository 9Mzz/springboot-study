package jpabook.start;

import javax.persistence.*;

@Entity
public class Locker {

    @Id
    @Column(name = "locker_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String name;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
