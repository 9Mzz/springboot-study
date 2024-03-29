package hello.ch04.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR", initialValue = 1, allocationSize = 1, sequenceName = "MEMBER_SEQ")
public class Member {

    @Id
    @Column(name = "member_id")
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private Long     id;
    @Column(name = "user_name")
    private String   userName;
    private Integer  age;
    // 추가
    @Enumerated
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date     createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date     lastModifiedDate;
    @Lob
    private String   description;

    //


}
