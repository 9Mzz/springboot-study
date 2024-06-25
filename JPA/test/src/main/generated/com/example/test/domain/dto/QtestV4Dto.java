package com.example.test.domain.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.test.domain.dto.QtestV4Dto is a Querydsl Projection type for testV4Dto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QtestV4Dto extends ConstructorExpression<testV4Dto> {

    private static final long serialVersionUID = 1818864449L;

    public QtestV4Dto(com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Integer> age, com.querydsl.core.types.Expression<String> description) {
        super(testV4Dto.class, new Class<?>[]{String.class, int.class, String.class}, name, age, description);
    }

}

