package hello.datajpatest.domain.dto;

import jakarta.annotation.sql.DataSourceDefinitions;
import lombok.Data;

@Data
public class MemberDto {

    private Long    id;
    private String  memberName;
    private Integer age;
    private String  street;
    private String  city;
    private String  teamName;

    public MemberDto() {
    }

    public MemberDto(Long id, String memberName, Integer age, String street, String city, String teamName) {
        this.id         = id;
        this.memberName = memberName;
        this.age        = age;
        this.street     = street;
        this.city       = city;
        this.teamName   = teamName;
    }
}
