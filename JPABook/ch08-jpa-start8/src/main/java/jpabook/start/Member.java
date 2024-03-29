package jpabook.start;

import javax.persistence.*;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long    id;
    @Column(name = "user_name")
    private String  username;
    private Integer age;

    // n
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id",
                nullable = false)
    private Team team;

    //con
    public Member() {
    }

    public Member(String username, Integer age) {
        this.username = username;
        this.age      = age;
    }

    public Member(Long id, String username, Integer age) {
        this.id       = id;
        this.username = username;
        this.age      = age;
    }

    //g&s
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
    //

    @Override
    public String toString() {
        return "Member{" + "id=" + id + ", username='" + username + '\'' + ", age=" + age + ", team=" + team + '}';
    }
}
