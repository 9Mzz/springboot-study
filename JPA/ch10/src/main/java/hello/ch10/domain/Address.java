package hello.ch10.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;
    //

    public Address(String city, String street, String zipcode) {
        this.city    = city;
        this.street  = street;
        this.zipcode = zipcode;
    }
    //

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
