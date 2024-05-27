package hello.datajpatest.domain;


import jakarta.persistence.*;
import lombok.*;
import org.aspectj.weaver.loadtime.Agent;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;
    private String  memberName;
    private Integer age;
    @Embedded
    private Address address;
    //
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_id")
    private Team    team;

    public Member(String memberName, Integer age, Address address, Team team) {
        this.memberName = memberName;
        this.age        = age;
        this.address    = address;
        if (team != null) {
            teamCheck(team);
        }
    }

    public Member(Long id, String memberName, Integer age, Address address, Team team) {
        this.id         = id;
        this.memberName = memberName;
        this.age        = age;
        this.address    = address;
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
