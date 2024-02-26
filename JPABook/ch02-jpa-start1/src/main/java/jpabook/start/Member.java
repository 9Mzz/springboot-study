package jpabook.start;

import javax.persistence.*;  //**

/**
 * User: HolyEyE Date: 13. 5. 24. Time: 오후 7:43
 */
@Entity
@org.hibernate.annotations.DynamicUpdate
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String  id;
    private String  username;

    @ManyToOne  //다대일(N:1) 관계 증명
    //생략 가능 -> 생략 시 기본 전략 ->  필드명 _ 참조 테이블 컬럼명 -> team_id
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    //const
    public Member() {
    }

    public Member(String id, String username) {
        this.id       = id;
        this.username = username;
    }

    //

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


    //TEAM G/S
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
