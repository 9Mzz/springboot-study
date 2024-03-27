package hello.ch01.domain;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;
    @Column(name = "id")
    private String  username;
    private Integer age;

    //
    public Member() {
    }
}
