package test.core.annotataion;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

//Qualifier 클래스의 어노테이션 전부 가져오기 + @Qualifier("mainDiscountPolicy") 어노테이션 추가
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
