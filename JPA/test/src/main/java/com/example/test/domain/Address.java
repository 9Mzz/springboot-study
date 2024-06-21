package com.example.test.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@Embeddable
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String street;
    private String city;

    public Address(String street, String city) {
        this.street = street;
        this.city   = city;
    }
}