package com.example.test.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class testV1Dto {

    private Long    id;
    private String  name;
    private Integer age;
    private String  city;
    private String  street;
    private String  teamName;

    @QueryProjection
    public testV1Dto(Long id, String name, Integer age, String city, String street, String teamName) {
        this.id       = id;
        this.name     = name;
        this.age      = age;
        this.city     = city;
        this.street   = street;
        this.teamName = teamName;
    }
}
