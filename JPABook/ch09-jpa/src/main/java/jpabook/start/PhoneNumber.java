package jpabook.start;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PhoneNumber {

    String areaCode;
    String localNumber;

    @ManyToOne
    @JoinColumn(name = "provider_name ")
    PhoneServiceProvider provider;

}
