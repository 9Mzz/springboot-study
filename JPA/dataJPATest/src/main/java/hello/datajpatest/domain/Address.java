package hello.datajpatest.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@ToString
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String street;
    private String city;

    public Address(String street, String city) {
        this.street = street;
        this.city   = city;
    }
}