package hello.datajpa.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long    id;
    private String  name;
    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String name, Integer age, Team team) {
        this.name = name;
        this.age  = age;
        if (team != null) {
            teamCreate(team);
        }
    }

    private void teamCreate(Team team) {
        this.team = team;
        team.getMembers()
                .add(this);
    }
}
