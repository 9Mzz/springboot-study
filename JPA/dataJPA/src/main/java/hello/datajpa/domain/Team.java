package hello.datajpa.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(exclude = {"members"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id
    @GeneratedValue
    private Long   id;
    private String name;

    //
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Member> members = new ArrayList<>();


}
