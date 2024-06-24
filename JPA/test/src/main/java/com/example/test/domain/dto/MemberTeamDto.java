package com.example.test.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class MemberTeamDto {

    private Long    memberId;
    private String  memberName;
    private Integer memberAge;
    private String  addressStreet;
    private String  addressCity;
    private String  teamName;

    @QueryProjection
    public MemberTeamDto(Long memberId, String memberName, Integer memberAge, String addressStreet, String addressCity, String teamName) {
        this.memberId      = memberId;
        this.memberName    = memberName;
        this.memberAge     = memberAge;
        this.addressStreet = addressStreet;
        this.addressCity   = addressCity;
        this.teamName      = teamName;
    }
}
