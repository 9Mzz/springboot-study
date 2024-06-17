package hello.querydsl.domain.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * hello.querydsl.domain.dto.QMemberDto is a Querydsl Projection type for MemberDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberDto extends ConstructorExpression<MemberDto> {

    private static final long serialVersionUID = 622596105L;

    public QMemberDto(com.querydsl.core.types.Expression<String> userName, com.querydsl.core.types.Expression<Integer> age) {
        super(MemberDto.class, new Class<?>[]{String.class, int.class}, userName, age);
    }

}

