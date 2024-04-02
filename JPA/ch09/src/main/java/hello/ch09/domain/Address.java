package hello.ch09.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Address {

    private String street;
    private String city;
    private String state;

    @Embedded
    private ZipCode zipCode;

    public Address(String street, String city, String state, ZipCode zipCode) {
        this.street  = street;
        this.city    = city;
        this.state   = state;
        this.zipCode = zipCode;
    }
}
