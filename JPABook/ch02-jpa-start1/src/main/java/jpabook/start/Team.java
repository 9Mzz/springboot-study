package jpabook.start;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long         id;
    private String       name;
    //주인 x
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();

    //
    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public Team(Long id, String name) {
        this.id   = id;
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
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


    //
    @Override
    public String toString() {
        return "Team{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
