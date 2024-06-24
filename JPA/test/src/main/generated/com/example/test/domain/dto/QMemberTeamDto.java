package com.example.test.domain.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.test.domain.dto.QMemberTeamDto is a Querydsl Projection type for MemberTeamDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberTeamDto extends ConstructorExpression<MemberTeamDto> {

    private static final long serialVersionUID = -1988644006L;

    public QMemberTeamDto(com.querydsl.core.types.Expression<Long> memberId, com.querydsl.core.types.Expression<String> memberName, com.querydsl.core.types.Expression<Integer> memberAge, com.querydsl.core.types.Expression<String> addressStreet, com.querydsl.core.types.Expression<String> addressCity, com.querydsl.core.types.Expression<String> teamName) {
        super(MemberTeamDto.class, new Class<?>[]{long.class, String.class, int.class, String.class, String.class, String.class}, memberId, memberName, memberAge, addressStreet, addressCity, teamName);
    }

}

