package hello.datajpatest.domain;

import hello.datajpatest.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.IdentityHashMap;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String memberName;
    private int    age;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team   team;

    //


    public Member(String memberName, int age, Team team) {
        this.memberName = memberName;
        this.age        = age;
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
