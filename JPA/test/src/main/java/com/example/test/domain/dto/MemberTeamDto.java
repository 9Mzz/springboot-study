package com.example.test.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberTeamDto {

    private Long    memberId;
    private String  memberName;
    private Integer age;
    private String  street;
    private String  city;
    private String  teamName;

    @QueryProjection
    public MemberTeamDto(Long memberId, String memberName, Integer age, String street, String city, String teamName) {
        this.memberId   = memberId;
        this.memberName = memberName;
        this.age        = age;
        this.street     = street;
        this.city       = city;
        this.teamName   = teamName;
    }
}
