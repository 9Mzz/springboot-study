package hello.jpa.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String userName;
    private Integer age;

    public Member() {
    }

    public Member(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }
}
