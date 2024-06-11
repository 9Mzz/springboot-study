package hello.querydsl.domain;

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
    private Long   id;
    private String userName;
    private int    age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    //


    public Member(String userName, int age) {
        this.userName = userName;
        this.age      = age;
    }

    public Member(String userName, int age, Team team) {
        this.userName = userName;
        this.age      = age;
        if (team != null) {
            teamCheck(team);
        }
    }

    private void teamCheck(Team team) {
        this.team = team;
        team.getMemberList()
                .add(this);
    }


}
