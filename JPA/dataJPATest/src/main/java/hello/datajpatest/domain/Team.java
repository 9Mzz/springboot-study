package hello.datajpatest.domain;

import jakarta.persistence.*;
import lombok.*;

import javax.print.attribute.standard.PrinterLocation;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "team")
@ToString(exclude = {"members"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String teamName;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Member> members = new ArrayList<>();

    //
    public Team(String teamName) {
        this.teamName = teamName;
    }
}