package hello.datajpa.domain;

import lombok.Data;

@Data
public class MemberDto {
    private Long   id;
    private String memberName;
    private int    age;
    private String teamName;

    public MemberDto(Long id, String memberName, int age, String teamName) {
        this.id         = id;
        this.memberName = memberName;
        this.age        = age;
        this.teamName   = teamName;
    }
}
