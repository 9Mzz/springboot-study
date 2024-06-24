package com.example.test.domain.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.test.domain.dto.QMemberCaseDto is a Querydsl Projection type for MemberCaseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberCaseDto extends ConstructorExpression<MemberCaseDto> {

    private static final long serialVersionUID = 5531047L;

    public QMemberCaseDto(com.querydsl.core.types.Expression<String> memberName, com.querydsl.core.types.Expression<Integer> memberAge, com.querydsl.core.types.Expression<String> description) {
        super(MemberCaseDto.class, new Class<?>[]{String.class, int.class, String.class}, memberName, memberAge, description);
    }

}

