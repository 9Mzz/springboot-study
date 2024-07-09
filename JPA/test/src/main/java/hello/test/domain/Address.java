package hello.test.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipCode;

    public Address(String city, String street, String zipCode) {
        this.city    = city;
        this.street  = street;
        this.zipCode = zipCode;
    }
}