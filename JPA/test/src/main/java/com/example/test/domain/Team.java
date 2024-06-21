package com.example.test.domain;

import jakarta.persistence.*;
import lombok.*;
import org.aspectj.weaver.ast.Literal;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = {"members"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", nullable = false)
    private Long   id;
    private String teamName;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Member> members = new ArrayList<>();


    //
    public Team(String teamName) {
        this.teamName = teamName;
    }
}