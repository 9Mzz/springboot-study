package hello.datajpa.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    private Long    id;
    private String  name;
    private Integer age;

    //
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    //


    public Member(String name, Integer age) {
        this.name = name;
        this.age  = age;
    }

    public Member(String name, Integer age, Team team) {
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
