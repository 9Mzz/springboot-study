package com.example.test.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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