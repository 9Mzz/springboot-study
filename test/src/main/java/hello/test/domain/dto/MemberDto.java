package hello.test.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class MemberDto {

    private Long   memberId;
    private String memberName;
    private String city;
    private String street;
    private int    zipCode;

    @QueryProjection
    public MemberDto(Long memberId, String memberName, String city, String street, int zipCode) {
        this.memberId   = memberId;
        this.memberName = memberName;
        this.city       = city;
        this.street     = street;
        this.zipCode    = zipCode;
    }
}
