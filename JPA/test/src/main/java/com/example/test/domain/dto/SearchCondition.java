package com.example.test.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchCondition {

    private String  memberName;
    private String  teamName;
    private Integer ageGoe;
    private Integer ageLoe;

    public SearchCondition(String memberName, String teamName, Integer ageGoe, Integer ageLoe) {
        this.memberName = memberName;
        this.teamName   = teamName;
        this.ageGoe     = ageGoe;
        this.ageLoe     = ageLoe;
    }
}
