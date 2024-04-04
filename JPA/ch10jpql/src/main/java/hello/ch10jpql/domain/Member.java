package hello.ch10jpql.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long   id;
    @ToString.Include
    private String memberName;
    @ToString.Include
    @Column(nullable = false)
    private int    age;

    //
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public Member(String memberName) {
        this.memberName = memberName;
    }

    public Member(String memberName, int age) {
        this.memberName = memberName;
        this.age        = age;
    }
    //

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", memberName='" + memberName + '\'' +
                ", age=" + age +
                '}';
    }
}
