package hello.querydsl.domain.condition;

import jakarta.annotation.sql.DataSourceDefinitions;
import lombok.Data;

@Data
public class MemberSearchCondition {

    // 회원 명
    private String  userName;
    // 팀 명
    private String  teamName;
    // 나이 Greater Equal
    private Integer ageGoe;
    // 나이 Low Equal
    private Integer ageLoe;
}
