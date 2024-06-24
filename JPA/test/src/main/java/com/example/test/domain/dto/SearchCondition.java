package com.example.test.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchCondition {

    private String  memberNameParam;
    private String  teamNameParam;
    private Integer ageGoe;
    private Integer ageLoe;

    public SearchCondition(String memberNameParam, String teamNameParam, Integer ageGoe, Integer ageLoe) {
        this.memberNameParam = memberNameParam;
        this.teamNameParam   = teamNameParam;
        this.ageGoe          = ageGoe;
        this.ageLoe          = ageLoe;
    }
}
