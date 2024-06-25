package com.example.test.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class testV4Dto {

    private String  name;
    private Integer age;
    private String  description;

    @QueryProjection
    public testV4Dto(String name, Integer age, String description) {
        this.name        = name;
        this.age         = age;
        this.description = description;
    }
}
