package hello.ch09.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String name;

    @Embedded
    @AttributeOverride(name = "street", column = @Column(name = "address_street"))
    private Address     address;
    @Embedded
    private PhoneNumber phoneNumber;

    //
    public Member(String name, Address address, PhoneNumber phoneNumber) {
        this.name        = name;
        this.address     = address;
        this.phoneNumber = phoneNumber;
    }
    //

}
