package hello.practice.domain;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Embeddable
@EqualsAndHashCode
public class Address {


    private String city;
    private String street;
    private String zipcode;

    public Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city    = city;
        this.street  = street;
        this.zipcode = zipcode;
    }

}
