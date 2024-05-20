package hello.datajpa.domain;

import lombok.Data;

@Data
public class MemberDto {

    private Long    memberId;
    private String  userName;
    private Integer age;
    private String  address;
    private String  teamName;

    public MemberDto(Long memberId, String userName, Integer age, String address, String teamName) {
        this.memberId = memberId;
        this.userName = userName;
        this.age      = age;
        this.address  = address;
        this.teamName = teamName;
    }
}
