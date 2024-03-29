package jpabook.start.jpql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_name")
    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Member> members = new ArrayList<>();

    //
    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }
    //

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    //

    @Override
    public String toString() {
        return "Team{" + "id=" + id + ", name='" + name + '\'' + ", members=" + members + '}';
    }
}
