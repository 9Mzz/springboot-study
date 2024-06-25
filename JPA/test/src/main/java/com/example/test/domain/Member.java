package com.example.test.domain;

import com.example.test.domain.baseentity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;
    private String  name;
    private int     age;
    @Embedded
    private Address address;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team    team;

    public Member(String name) {
        this.name = name;
    }

    public Member(String name, int age, Address address, Team team) {
        this.name    = name;
        this.age     = age;
        this.address = address;
        if (team != null) {
            teamCheck(team);
        }
    }

    private void teamCheck(Team team) {
        this.team = team;
        team.getMembers()
                .add(this);
    }
}
