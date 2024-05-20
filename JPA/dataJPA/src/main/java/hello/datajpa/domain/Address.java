package hello.datajpa.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String memberAddress;

    public Address(String memberAddress) {
        this.memberAddress = memberAddress;
    }
}
