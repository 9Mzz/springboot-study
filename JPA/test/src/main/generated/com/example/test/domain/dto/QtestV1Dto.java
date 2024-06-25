package com.example.test.domain.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.test.domain.dto.QtestV1Dto is a Querydsl Projection type for testV1Dto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QtestV1Dto extends ConstructorExpression<testV1Dto> {

    private static final long serialVersionUID = 1818775076L;

    public QtestV1Dto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Integer> age, com.querydsl.core.types.Expression<String> city, com.querydsl.core.types.Expression<String> street, com.querydsl.core.types.Expression<String> teamName) {
        super(testV1Dto.class, new Class<?>[]{long.class, String.class, int.class, String.class, String.class, String.class}, id, name, age, city, street, teamName);
    }

}

