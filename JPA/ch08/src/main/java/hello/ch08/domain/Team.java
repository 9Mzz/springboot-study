package hello.ch08.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Team {

    @Id
    @Column(name = "team_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long   id;
    private String teamName;

    @OneToMany(mappedBy = "team", orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    //

    public Team(String teamName) {
        this.teamName = teamName;
    }

    //
    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "teamName = " + teamName + ")";
    }
}
