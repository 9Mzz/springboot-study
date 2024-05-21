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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;
    private String  userName;
    private Integer age;
    
    @Embedded
    private Address address;

    //
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_id")
    private Team team;

    //
    public Member(String userName) {
        this.userName = userName;
    }

    public Member(String userName, Integer age) {
        this.userName = userName;
        this.age      = age;
    }

    public Member(String userName, Integer age, Address address) {
        this.userName = userName;
        this.age      = age;
        this.address  = address;
    }

    public Member(String userName, Integer age, Address address, Team team) {
        this.userName = userName;
        this.age      = age;
        this.address  = address;
        if (team != null) {
            teamCheck(team);
        }
    }

    public Member(String userName, Integer age, Team team) {
        this.userName = userName;
        this.age      = age;
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
