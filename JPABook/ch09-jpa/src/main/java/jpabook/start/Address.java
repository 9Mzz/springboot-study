package jpabook.start;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Address {


    private String  street;
    private String  city;
    private String  state;
    @Embedded
    private Zipcode zipcode;

    //

    public Address() {
    }

    public Address(String street, String city, String state) {
        this.street = street;
        this.city   = city;
        this.state  = state;
    }

    public Address(String city, String state, Zipcode zipcode) {
        this.city    = city;
        this.state   = state;
        this.zipcode = zipcode;
    }

    //
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Zipcode getZipcode() {
        return zipcode;
    }

    public void setZipcode(Zipcode zipcode) {
        this.zipcode = zipcode;
    }
    //

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
