package hello.datajpa.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString(of = {"id", "name", "age"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String name;
    private int    age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    //

    public Member(String name, int age) {
        this.name = name;
        this.age  = age;
    }

    public Member(String name, int age, Team team) {
        this.name = name;
        this.age  = age;
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
