package jpabook.start;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;

@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long   id;
    private String name;
    private int    age;

    @Embedded
//    @AttributeOverride(name = "street", column = @Column(name = "COMPANY_STREET"))
//    @AttributeOverride(name = "city", column = @Column(name = "COMPANY_CITY"))
//    @AttributeOverride(name = "state", column = @Column(name = "COMPANY_STATE"))
    private Address address;

    @Embedded
    private PhoneNumber phoneNumber;

    //


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
