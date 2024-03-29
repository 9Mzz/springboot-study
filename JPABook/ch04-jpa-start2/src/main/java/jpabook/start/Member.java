package jpabook.start;

import javax.persistence.*;  //**
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: HolyEyE Date: 13. 5. 24. Time: 오후 7:43
 */
//@Entity
//@Table(name = "MEMBER",
//       uniqueConstraints = {@UniqueConstraint(name = "NAME_AGE_UNIQUE",
//                                              columnNames = {"NAME", "AGE"})})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String   id;
    @Column(name = "NAME",
            nullable = false,
            length = 10)
    //    @Column(name = "NAME")
    private String   username;
    //    @Column(unique = true)
    private Integer  age;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date     createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date     lastModifiedDate;
    @Lob
    private String   description;
    @Transient
    private String   temp;
    //

    //Getter, Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
