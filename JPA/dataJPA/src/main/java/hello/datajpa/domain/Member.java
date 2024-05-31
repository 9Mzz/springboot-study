package hello.datajpa.domain;


import hello.datajpa.domain.base.BaseEntity;
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
    private Long   id;
    private String userName;
    private int    age;

    //
    @JoinColumn(name = "team_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    public Member(String userName) {
        this.userName = userName;
    }

    //
    public Member(String userName, int age, Team team) {
        this.userName = userName;
        this.age      = age;
        teamCheck(team);
    }

    private void teamCheck(Team team) {
        this.team = team;
        team.getMembers()
                .add(this);
    }
}
