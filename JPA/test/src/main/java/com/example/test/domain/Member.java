package com.example.test.domain;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;
    private String  memberName;
    private int     age;
    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    //
    public Member(String memberName, int age, Address address, Team team) {
        this.memberName = memberName;
        this.age        = age;
        this.address    = address;
        this.team       = team;
    }
}
