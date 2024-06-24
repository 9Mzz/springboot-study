package com.example.test.domain;

import com.example.test.config.baseentity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;
    private String  memberName;
    private int     age;
    @Embedded
    private Address address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team    team;

    //
    public Member(String memberName, int age, Address address, Team team) {
        this.memberName = memberName;
        this.age        = age;
        this.address    = address;
        teamCheck(team);
    }

    private void teamCheck(Team team) {
        this.team = team;
        team.getMembers()
                .add(this);
    }
}
