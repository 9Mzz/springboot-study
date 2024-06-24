package com.example.test.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class MemberCaseDto {


    private String  memberName;
    private Integer memberAge;
    private String  description;

    @QueryProjection
    public MemberCaseDto(String memberName, Integer memberAge, String description) {
        this.memberName  = memberName;
        this.memberAge   = memberAge;
        this.description = description;
    }
}
